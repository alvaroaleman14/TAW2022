package es.taw.carrito.dao;

import es.taw.carrito.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    @Query ("SELECT p FROM Producto p WHERE p.disponible = true AND p.cantidad > 0 AND p.productoId not in :idsproductos")
    public List<Producto> getProductosDisponibles(@Param("idsproductos") List<Integer> idsproductos);

    @Query ("SELECT p FROM Producto p WHERE p.disponible = true AND p.cantidad > 0")
    public List<Producto> getProductosDisponibles();


}
