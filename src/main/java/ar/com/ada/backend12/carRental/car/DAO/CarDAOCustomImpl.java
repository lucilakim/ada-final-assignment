package ar.com.ada.backend12.carRental.car.DAO;

import ar.com.ada.backend12.carRental.car.model.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarDAOCustomImpl implements CarDAOCustom{
    private static final Logger logger = LoggerFactory.getLogger(CarDAOCustomImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> getAll(Integer typeId, Integer passengersNumber, String airConditioning, BigDecimal dailyRent) {
        StringBuilder queryBuilder = new StringBuilder("select c from Car c ");

        List<String> conditions = new ArrayList<>();

        if(typeId != null) {
            conditions.add("c.typeId = : typeId");
        }
        if(passengersNumber != null) {
            conditions.add("c.passengersNumber = :passengersNumber");
        }
        if(airConditioning != null) {
            conditions.add("c.airConditioning = :airConditioning");
        }
        if(dailyRent != null) {
            conditions.add("c.dailyRent = :dailyRent");
        }

        if(conditions.size() > 0) {
            queryBuilder.append("where ");
            for (int i = 0; i < conditions.size(); i++) {
                queryBuilder.append(conditions.get(i));
                if (conditions.size() - 1 != i) {
                    queryBuilder.append(" and ");
                }
            }
        }

        String queryString = queryBuilder.toString();

        logger.info(queryString);

        Query query = entityManager.createQuery(queryString);

        if(typeId != null) {query.setParameter("typeId", typeId);}
        if(passengersNumber != null) {query.setParameter("passengersNumber", passengersNumber);}
        if(airConditioning != null) {query.setParameter("airConditioning", airConditioning);}
        if(dailyRent != null) {query.setParameter("dailyRent", dailyRent);}

        return query.getResultList();
    }
}
