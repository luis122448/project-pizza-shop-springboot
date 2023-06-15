package luis122448.projectpizza.persistence.entity.audit;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import luis122448.projectpizza.persistence.entity.PizzaEntity;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {

    private PizzaEntity currentValue;
    @PostLoad
    public void postLoad(PizzaEntity p){
        System.out.println(p);
        this.currentValue = SerializationUtils.clone(p);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersis(PizzaEntity p){
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE : " + this.currentValue);
        System.out.println("NEW VALUE : " + p.toString());
    }

    @PreRemove
    public void onPreDelete(PizzaEntity p){
        System.out.println(p.toString());
    }
}
