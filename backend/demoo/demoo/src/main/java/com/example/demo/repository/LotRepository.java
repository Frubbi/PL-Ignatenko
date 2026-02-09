package com.example.demo.repository;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jooqdata.tables.Lot.LOT;
import jooqdata.tables.records.LotRecord;

@Repository
public class LotRepository {

    private final DSLContext dsl;

    public LotRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<LotRecord> findAll() {
        return dsl
                .selectFrom(LOT)
                .fetch();
    }

    public void insert(LotRecord lot) {
        dsl
                .insertInto(LOT)
                .set(lot)
                .execute();
    }

    public void update(LotRecord lot) {
        dsl
                .update(LOT)
                .set(lot)
                .where(LOT.LOT_ID.eq(lot.getLotId())) // если есть id
                .execute();
    }

    public void delete(String lotName, String customerCode) {
        dsl.deleteFrom(LOT)
                .where(LOT.LOT_NAME.eq(lotName)
                        .and(LOT.CUSTOMER_CODE.eq(customerCode)))
                .execute();
    }
}
