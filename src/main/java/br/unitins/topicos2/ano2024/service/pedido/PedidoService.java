package br.unitins.topicos2.ano2024.service.pedido;

import java.util.List;

import br.unitins.topicos2.ano2024.dto.pedido.CartaoCreditoDTO;
import br.unitins.topicos2.ano2024.dto.pedido.ItemPedidoDTO;
import br.unitins.topicos2.ano2024.dto.pedido.PedidoResponseDTO;

public interface PedidoService {
    List<PedidoResponseDTO> getAll (Long idUsuario);

    PedidoResponseDTO getPedidoEmAndamento (Long idUsuario);

    void insertItemIntoPedido (Long idUsuario, ItemPedidoDTO itemPedidoDTO);

    void removeItemPedido (Long idUsuario, Long idItemPedido);

    void efetuarPagamentoBoleto(Long idUsuario);

    void efetuarPagamentoPix(Long idUsuario);

    void efetuarPagamentoCartaoCredito(Long idUsuario, CartaoCreditoDTO cartaoCreditoDTO);

    void cancelarPedido(Long idUsuario);

    void finishPedido (Long idPedido);
}
