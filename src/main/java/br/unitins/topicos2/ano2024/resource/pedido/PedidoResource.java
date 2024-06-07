package br.unitins.topicos2.ano2024.resource.pedido;

import org.jboss.logging.Logger;

import br.unitins.topicos2.ano2024.application.Result;
import br.unitins.topicos2.ano2024.dto.pedido.CartaoCreditoDTO;
import br.unitins.topicos2.ano2024.dto.pedido.ItemPedidoDTO;
import br.unitins.topicos2.ano2024.model.usuario.Usuario;
import br.unitins.topicos2.ano2024.service.pedido.PedidoService;
import br.unitins.topicos2.ano2024.service.usuario.UsuarioService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


@Path("/pedidos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService pedidoService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JsonWebToken tokenJwt;

    private static final Logger LOG = Logger.getLogger(PedidoResource.class);

    @GET
    @Path("/historico-pedidos")
    @RolesAllowed({ "User" })
    public Response getAll() {
        Result result = null;

        String login = tokenJwt.getSubject();

        Usuario usuario = usuarioService.getByLogin(login);

        try {
            LOG.infof("Buscando histórico de pedidos para o usuário: " + usuario.getId());
            return Response.ok(pedidoService.getAll(usuario.getId())).build();
        } catch (NullPointerException e) {
            LOG.error("Erro ao recuperar histórico de pedidos.", e);

            result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @GET
    @Path("/carrinho")
    @RolesAllowed({ "User", "User_Basic" })
    public Response getPedidoEmAndamento() {
        Result result = null;

        String login = tokenJwt.getSubject();

        Usuario usuario = usuarioService.getByLogin(login);

        try {
            LOG.infof("Buscando pedido em andamento para o usuário: " + usuario.getId());

            return Response.ok(pedidoService.getPedidoEmAndamento(usuario.getId())).build();
        } catch (NullPointerException e) {
            LOG.error("Erro ao buscar pedido em andamento.", e);
            result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @POST
    @Path("/carrinho/adiconar-item")
    @RolesAllowed({ "User", "User_Basic" })
    public Response insertIntoCarrrinho(ItemPedidoDTO itemPedidoDTO) {
        Result result = null;

        try {

            String login = tokenJwt.getSubject();

            Usuario usuario = usuarioService.getByLogin(login);

            pedidoService.insertItemIntoPedido(usuario.getId(), itemPedidoDTO);

            LOG.info("Item inserido no carrinho com sucesso.");
            return Response.status(Status.CREATED).build();
        } catch (NullPointerException e) {
            LOG.error("Erro ao adicionar item no carrinho.", e);

            result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PATCH
    @Path("/carrinho/remover-item/{idProduto}")
    @RolesAllowed({ "User", "User_Basic" })
    public Response removeItemFromCarrinho(@PathParam("idProduto") Long idProduto) {
        Result result = null;

        try {

            String login = tokenJwt.getSubject();

            Usuario usuario = usuarioService.getByLogin(login);

            pedidoService.removeItemPedido(usuario.getId(), idProduto);

            LOG.info("Item removido do carrinho com sucesso.");
            return Response.status(Status.NO_CONTENT).build();
        } catch (NullPointerException e) {
            LOG.error("Erro ao remover item do carrinho.", e);

            result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/carrinho/cancelar-pedido")
    @RolesAllowed({ "User", "User_Basic" })
    public Response cancelarPedido() {
        Result result = null;

        try {

            String login = tokenJwt.getSubject();

            Usuario usuario = usuarioService.getByLogin(login);

            pedidoService.cancelarPedido(usuario.getId());

            LOG.info("Pedido cancelada com sucesso.");
            return Response.status(Status.NO_CONTENT).build();
        } catch (NullPointerException e) {
            LOG.error("Erro ao cancelar pedido.", e);

            result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PATCH
    @Path("/carrinho/pagar-boleto-bancario")
    @RolesAllowed({ "User" })
    public Response pagarBoletoBancario() {

        try {

            String login = tokenJwt.getSubject();

            Usuario usuario = usuarioService.getByLogin(login);

            pedidoService.efetuarPagamentoBoleto(usuario.getId());

            LOG.info("Pagamento com boleto efetuado com sucesso.");
            return Response.status(Status.ACCEPTED).build();
        } catch (

        NullPointerException e) {

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PATCH
    @Path("/carrinho/pagar-pix")
    @RolesAllowed({ "User" })
    public Response pagarPix() {
        
        Result result = null;

       try {

            String login = tokenJwt.getSubject();

            Usuario usuario = usuarioService.getByLogin(login);

            pedidoService.efetuarPagamentoPix(usuario.getId());

            LOG.info("Pagamento com pix efetuado com sucesso.");
            return Response.status(Status.ACCEPTED).build(); 
        }
        
         catch (NullPointerException e) {
             LOG.error("Erro ao efetuar o pagamento com pix.", e);
             result = new Result(e.getMessage(), false);

             return Response.status(Status.NOT_FOUND).entity(result).build();
         }
    }

    @PATCH
    @Path("/carrinho/pagar-cartao-credito")
    @RolesAllowed({ "User" })
    public Response pagarCartaoCredito(CartaoCreditoDTO cartaoCreditoDTO) {
        Result result = null;

        try {

            String login = tokenJwt.getSubject();

            Usuario usuario = usuarioService.getByLogin(login);

            pedidoService.efetuarPagamentoCartaoCredito(usuario.getId(), cartaoCreditoDTO);

            LOG.info("Pagamento com cartão de crédito efetuado com sucesso.");
            return Response.status(Status.ACCEPTED).build();
        } catch (NullPointerException e) {
            LOG.error("Erro ao efetuar o pagamento com cartão de crédito.", e);
            result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }
}

