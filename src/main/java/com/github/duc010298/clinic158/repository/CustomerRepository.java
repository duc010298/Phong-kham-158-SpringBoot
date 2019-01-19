package com.github.duc010298.clinic158.repository;

import com.github.duc010298.clinic158.entity.CustomerEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    @Query("SELECT c.customerName FROM CustomerEntity c WHERE c.nameSearch LIKE ?1 GROUP BY c.customerName")
    List<String> searchByName(String value, Pageable pageable);

    default List<String> searchTop10ByName(String value) {
        return searchByName('%' + value + '%', new PageRequest(0, 10));
    }

    @Query("SELECT c.yob FROM CustomerEntity c WHERE str(c.yob) LIKE ?1 GROUP BY c.yob")
    List<Integer> searchByYob(String value, Pageable pageable);

    default List<String> searchTop10ByYob(String value) {
        List<Integer> integerList = searchByYob('%' + value + '%', new PageRequest(0, 10));
        List<String> ret = new ArrayList<>();
        for (Integer integer : integerList) {
            ret.add(integer.toString());
        }
        return ret;
    }

    @Query("SELECT c.address FROM CustomerEntity c WHERE c.addressSearch LIKE ?1 GROUP BY c.address")
    List<String> searchByAddress(String value, Pageable pageable);

    default List<String> searchTop10ByAddress(String value) {
        return searchByAddress('%' + value + '%', new PageRequest(0, 10));
    }

    @Query("FROM CustomerEntity c WHERE c.nameSearch LIKE ?1 AND c.addressSearch LIKE ?2 ORDER BY c.dayVisit DESC")
    List<CustomerEntity> searchCustomerWithoutYobAndDayVisit(String nameSearch, String addressSearch, Pageable pageable);

    default List<CustomerEntity> searchTop100CustomerWithoutYobAndDayVisit(String nameSearch, String addressSearch) {
        return searchCustomerWithoutYobAndDayVisit('%' + nameSearch + '%', '%' + addressSearch + '%', new PageRequest(0, 100));
    }

    @Query("FROM CustomerEntity c WHERE c.nameSearch LIKE ?1 AND str(c.yob) LIKE ?2 AND c.addressSearch LIKE ?3 ORDER BY c.dayVisit DESC")
    List<CustomerEntity> searchCustomerWithoutDayVisit(String nameSearch, String yob, String addressSearch, Pageable pageable);

    default List<CustomerEntity> searchTop100CustomerWithoutDayVisit(String nameSearch, Integer yob, String addressSearch) {
        return searchCustomerWithoutDayVisit('%' + nameSearch + '%', '%' + yob.toString() + '%', '%' + addressSearch + '%', new PageRequest(0, 100));
    }

    @Query("FROM CustomerEntity c WHERE c.nameSearch LIKE ?1 AND c.addressSearch LIKE ?2 AND c.dayVisit BETWEEN ?3 AND ?4 ORDER BY c.dayVisit ASC")
    List<CustomerEntity> searchCustomerWithoutYob(String nameSearch, String addressSearch, Date startDate, Date endDate, Pageable pageable);

    default List<CustomerEntity> searchTop100CustomerWithoutYob(String nameSearch, String addressSearch, Date startDate) {
        return searchCustomerWithoutYob('%' + nameSearch + '%', '%' + addressSearch + '%', startDate, new Date(), new PageRequest(0, 100));
    }

    @Query("FROM CustomerEntity c WHERE c.nameSearch LIKE ?1 AND str(c.yob) LIKE ?2 AND c.addressSearch LIKE ?3 AND c.dayVisit BETWEEN ?4 AND ?5 ORDER BY c.dayVisit ASC")
    List<CustomerEntity> searchCustomer(String nameSearch, String yob, String addressSearch, Date startDate, Date endDate, Pageable pageable);

    default List<CustomerEntity> searchTop100Customer(String nameSearch, Integer yob, String addressSearch, Date startDate) {
        return searchCustomer('%' + nameSearch + '%', '%' + yob.toString() + '%','%' + addressSearch + '%', startDate, new Date(), new PageRequest(0, 100));
    }

    @Query("SELECT c.report FROM CustomerEntity c WHERE c.id = ?1")
    String getReport(Integer id);
}
