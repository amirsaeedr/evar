package ir.iust.computer.ood.evar.repository;

import ir.iust.computer.ood.evar.model.Advertise;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertiseRepository extends MongoRepository<Advertise, String>, QuerydslPredicateExecutor<Advertise> {
}
