package br.unitins.topicos2.ano2024.dto.pedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unitins.topicos2.ano2024.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos2.ano2024.model.pedido.ItemPedido;
import br.unitins.topicos2.ano2024.model.pedido.Pedido;

public record PedidoResponseDTO(
    LocalDate dataCompra,
    String valorTotal,
    List<Map<String, Object>> itensPedido,
    Map<String, Object> endereco,
    String statusPagamento,
    LocalDate dataPagamento,
    String statusCompra
) {
    
    public PedidoResponseDTO(Pedido pedido) {
        this(
            pedido.getDataCompra(),
            "R$ " + String.format("%.2f", pedido.getValorTotal()),
            pedido.getItemPedido() != null ? 
                viewItensPedido(pedido.getItemPedido()) : 
                null,
            pedido.getEndereco() != null?
                UsuarioResponseDTO.viewEndereco(pedido.getEndereco().getCep(),
                                        pedido.getEndereco().getBairro(),
                                        pedido.getEndereco().getLogradouro(),
                                        pedido.getEndereco().getNumero(),
                                        pedido.getEndereco().getComplemento()) :
                null,
            pedido.getPagamento() != null
                ? pedido.getPagamento().getConfirmacaoPagamento() == true? 
                    "Pagamento efetuado" : 
                    "Pagamento não efetuado"
                : null,
            pedido.getPagamento() != null ? 
                pedido.getPagamento().getDataConfirmacaoPagamento() : 
                null,
            pedido.getIfConcluida() == true ? "Compra concluída" : "Compra em andamento"
        );
    }

    private static List<Map<String, Object>> viewItensPedido (List<ItemPedido> lista) {

        List<Map<String, Object>> listaitensPedido = new ArrayList<>();

        for (ItemPedido itensPedido : lista) {
            
            Map<String, Object> itemPedido = new HashMap<>();

            itemPedido = viewItemPedido(itensPedido.getProduto().getNome(), itensPedido.getSubTotal(), itensPedido.getQuantidade());

            listaitensPedido.add(itemPedido);
        }

        return listaitensPedido;
    }

    private static Map<String, Object> viewItemPedido (String nome, Double valor, Integer quantidade) {

        Map<String, Object> itemPedido = new HashMap<>();

        itemPedido.put("nome", nome);
        itemPedido.put("valor", valor);
        itemPedido.put("quantidade", quantidade);

        return itemPedido;
    }
}
