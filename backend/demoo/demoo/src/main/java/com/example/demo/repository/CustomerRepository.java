package com.example.demo.repository;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jooqdata.tables.Customer.CUSTOMER;
import jooqdata.tables.records.CustomerRecord;

@Repository
public class CustomerRepository {

    private final DSLContext dsl;

    public CustomerRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    /** Получить всех контрагентов */
    public List<CustomerRecord> findAll() {
        return dsl
                .selectFrom(CUSTOMER)
                .fetch();
    }

    /** Найти по коду */
    public CustomerRecord findByCode(String code) {
        return dsl
                .selectFrom(CUSTOMER)
                .where(CUSTOMER.CUSTOMER_CODE.eq(code))
                .fetchOne();
    }

    /** Добавить */
    public void insert(CustomerRecord customer) {
        dsl
                .insertInto(CUSTOMER)
                .set(customer)
                .execute();
    }

    /** Обновить */
    //public void update(CustomerRecord customer) {
      //  dsl
       //         .update(CUSTOMER)
       //         .set(customer)
      //          .where(CUSTOMER.CUSTOMER_CODE.eq(customer.getCustomerCode()))
       //         .execute();
   // }
    public void update(CustomerRecord customer) {
        dsl.update(CUSTOMER)
                .set(CUSTOMER.CUSTOMER_NAME, customer.getCustomerName())
                .set(CUSTOMER.CUSTOMER_INN, customer.getCustomerInn())
                .set(CUSTOMER.CUSTOMER_KPP, customer.getCustomerKpp())
                .set(CUSTOMER.CUSTOMER_LEGAL_ADDRESS, customer.getCustomerLegalAddress())
                .set(CUSTOMER.CUSTOMER_POSTAL_ADDRESS, customer.getCustomerPostalAddress())
                .set(CUSTOMER.CUSTOMER_EMAIL, customer.getCustomerEmail())
                .set(CUSTOMER.CUSTOMER_CODE_MAIN, customer.getCustomerCodeMain())
                .set(CUSTOMER.IS_ORGANIZATION, customer.getIsOrganization())
                .set(CUSTOMER.IS_PERSON, customer.getIsPerson())
                .where(CUSTOMER.CUSTOMER_CODE.eq(customer.getCustomerCode()))
                .execute();
    }

    /** Удалить */
    public void delete(String code) {
        dsl
                .deleteFrom(CUSTOMER)
                .where(CUSTOMER.CUSTOMER_CODE.eq(code))
                .execute();
    }
}
