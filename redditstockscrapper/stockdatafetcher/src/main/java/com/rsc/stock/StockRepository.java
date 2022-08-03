package com.rsc.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query(
            value = "SELECT setval( 'stock_id_seq', 1, false);",
            nativeQuery = true
    )
    void resetPrimaryKey();
}
