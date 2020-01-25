package ir.iust.computer.ood.evar.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdvertise is a Querydsl query type for Advertise
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAdvertise extends EntityPathBase<Advertise> {

    private static final long serialVersionUID = 789091863L;

    public static final QAdvertise advertise = new QAdvertise("advertise");

    public final StringPath advertiseId = createString("advertiseId");

    public final MapPath<String, String, StringPath> advertiseSpec = this.<String, String, StringPath>createMap("advertiseSpec", String.class, String.class, StringPath.class);

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public final NumberPath<Integer> rate = createNumber("rate", Integer.class);

    public final StringPath title = createString("title");

    public QAdvertise(String variable) {
        super(Advertise.class, forVariable(variable));
    }

    public QAdvertise(Path<? extends Advertise> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdvertise(PathMetadata metadata) {
        super(Advertise.class, metadata);
    }

}

