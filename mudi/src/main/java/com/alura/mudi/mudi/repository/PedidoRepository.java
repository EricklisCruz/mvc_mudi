package com.alura.mudi.mudi.repository;

import com.alura.mudi.mudi.model.Pedido;
import com.alura.mudi.mudi.model.StatusPedido;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByStatus(StatusPedido status, Pageable sort);

    @Query("select p from Pedido p join p. user u where u.username = :username")
    List<Pedido> findAllByUser(@Param("username") String username);

    @Query("select p from Pedido p join p.user u where u.username = :username and p.status = :status")
    List<Pedido> findByStatusAndUser(@Param("status") StatusPedido status, @Param("username") String username);
}
