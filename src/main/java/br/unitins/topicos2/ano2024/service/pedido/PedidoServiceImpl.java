package br.unitins.topicos2.ano2024.service.pedido;

import java.time.LocalDate;
import java.util.List;

import br.unitins.topicos2.ano2024.dto.pedido.CartaoCreditoDTO;
import br.unitins.topicos2.ano2024.dto.pedido.ItemPedidoDTO;
import br.unitins.topicos2.ano2024.dto.pedido.PedidoResponseDTO;
import br.unitins.topicos2.ano2024.model.pagamento.CartaoCredito;
import br.unitins.topicos2.ano2024.model.pagamento.Pix;
import br.unitins.topicos2.ano2024.model.pedido.ItemPedido;
import br.unitins.topicos2.ano2024.model.pedido.Pedido;
import br.unitins.topicos2.ano2024.model.produto.Produto;
import br.unitins.topicos2.ano2024.model.usuario.Usuario;
import br.unitins.topicos2.ano2024.repository.pedido.PedidoRepository;
import br.unitins.topicos2.ano2024.repository.usuario.UsuarioRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    ItemPedidoRepository itemPedidoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    PanacheRepository<? extends Produto> produtoRepository;

    @Inject
    PixRepository pixRepository;

    @Inject
    CartaoCreditoRepository cartaoCreditoRepository;

    @Override
    public List<PedidoResponseDTO> getAll(Long idUsuario) {
        
        List<Pedido> list = pedidoRepository.findByUsuarioWhereIsFinished(usuarioRepository.findById(idUsuario));

        if (list == null)
            throw new NullPointerException("Pedido não encontrada");

        return list.stream().map(PedidoResponseDTO::new).toList();
    }

    @Override
    public PedidoResponseDTO getPedidoEmAndamento(Long idUsuario) {
        
        Pedido pedido = pedidoRepository.findByUsuarioWhereIsNotFinished(usuarioRepository.findById(idUsuario));

        if (pedido == null)
            throw new NullPointerException("Nenhuma Pedido em andamento");

        return new PedidoResponseDTO(pedido);
    }

    @Override
    @Transactional
    public void insertItemIntoPedido(Long idUsuario, ItemPedidoDTO itemPedidoDTO) throws NullPointerException {
        
        Produto produto = validar(itemPedidoDTO);

        Pedido pedido = validar(idUsuario);

        Integer indice = validar(produto, pedido.getItemPedido());

        ItemPedido itemPedido;

        if (indice != null) {

            itemPedido = pedido.getItemPedido().get(indice);

            itemPedido.updateQuantidade(itemPedidoDTO.quantidade());

            pedido.plusTotalPedido(itemPedido.getSubTotal() * itemPedidoDTO.quantidade());
        }

        else {

            itemPedido = new ItemPedido(produto, itemPedidoDTO.quantidade());

            itemPedidoRepository.persist(itemPedido);

            pedido.setItemPedido(itemPedido);

            pedido.plusTotalPedido(itemPedido.getSubTotal() * itemPedido.getQuantidade());
        }
    }

    @Override
    @Transactional
    public void removeItemPedido(Long idUsuario, Long idProduto) {
        
        Pedido pedido = pedidoRepository.findByUsuarioWhereIsNotFinished(usuarioRepository.findById(idUsuario));

        if (pedido == null)
            throw new NullPointerException("Não há nenhuma Pedido em andamento");

        ItemPedido itemPedido = itemPedidoRepository.findByProduto(produtoRepository.findById(idProduto));

        pedido.minusTotalPedido(itemPedido.getSubTotal() * itemPedido.getQuantidade());

        pedido.getItemPedido().remove(itemPedido);
    }

    @Override
    @Transactional
    public void cancelarPedido(Long idUsuario) {
        
        Pedido pedido = pedidoRepository.findByUsuarioWhereIsNotFinished(usuarioRepository.findById(idUsuario));

        if (pedido == null)
            throw new NullPointerException("Não há nenhuma Pedido em andamento");

        for (ItemPedido itemPedido : pedido.getItemPedido()) {

            itemPedidoRepository.delete(itemPedido);
        }

        pedidoRepository.delete(pedido);
    }

    @Override
    public void finishPedido(Long idPedido) throws NullPointerException {

        Pedido pedido = pedidoRepository.findById(idPedido);

        pedido.setDataPedido(LocalDate.now());

        pedido.setEndereco(pedido.getUsuario().getEndereco());

        pedido.setIfConcluida(true);
    }

    @Override
    @Transactional
    public void efetuarPagamentoPix(Long idUsuario) {
        
        Usuario usuario = usuarioRepository.findById(idUsuario);
        
        Pedido pedido = validar(usuario);

        Pix pagamento = new Pix(pedido.getValoTotal(), pedido.getUsuario().getPessoaFisica().getNome(), pedido.getUsuario().getPessoaFisica().getCpf());

        pixRepository.persist(pagamento);

        pedido.setPagamento(pagamento);

        if (pedido.getPagamento() == null)
            throw new NullPointerException("Não foi efetuado nenhum pagamento");

        finishPedido(pedido.getId());
    }

    @Override
    @Transactional
    public void efetuarPagamentoCartaoCredito(Long idUsuario, CartaoCreditoDTO cartaoCreditoDTO) {
        
        Usuario usuario = usuarioRepository.findById(idUsuario);

        Pedido pedido = validar(usuario);

        CartaoCredito pagamento = new CartaoCredito(pedido.getTotalPedido(),
                                            cartaoCreditoDTO.numeroCartao(),
                                            cartaoCreditoDTO.nomeImpressoCartao(),
                                            usuario.getPessoaFisica().getCpf(),
                                            BandeiraCartao.valueOf(cartaoCreditoDTO.bandeiraCartao()));
        
        cartaoCreditoRepository.persist(pagamento);

        pedido.setPagamento(pagamento);

        if (pedido.getPagamento() == null)
            throw new NullPointerException("Não foi efetuado nenhum pagamento");

        finishPedido(pedido.getId());
    }

    private Pedido validar(Usuario usuario) throws NullPointerException {

        Pedido pedido = pedidoRepository.findByUsuarioWhereIsNotFinished(usuario);

        if (pedido == null)
            throw new NullPointerException("Não há nenhuma Pedido em andamento");

        if (pedido.getItemPedido().size() == 0)
            throw new NullPointerException("Não há nenhum item dentro do carrinho");

        return pedido;
    }

    private Integer validar(Produto produto, List<ItemPedido> listaItens) {

        for (int i = 0; i < listaItens.size(); i++) {
            
            if (listaItens.get(i).contains(produto))
                return i;
        }

        return null;
    }

    private Pedido validar(Long idUsuario) {

        Pedido pedido = pedidoRepository.findByUsuarioWhereIsNotFinished(usuarioRepository.findById(idUsuario));

        if (pedido == null) {

            Pedido newPedido = new Pedido(usuarioRepository.findById(idUsuario));

            pedidoRepository.persist(newPedido);

            return newPedido;
        }

        else
            return pedido;
    }

    private Produto validar(ItemPedidoDTO itemPedidoDTO) throws NullPointerException {

        Produto produto = produtoRepository.findById(itemPedidoDTO.idProduto());

    }
}