package com.avaje.ebeaninternal.server.persist.dmlbind;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.avaje.ebean.bean.EntityBean;
import com.avaje.ebeaninternal.server.core.PersistRequestBean;
import com.avaje.ebeaninternal.server.deploy.BeanPropertyCompound;
import com.avaje.ebeaninternal.server.persist.dml.GenerateDmlRequest;

/**
 * Bindable for a Immutable Compound value object.
 */
public class BindableCompound implements Bindable {

    private final Bindable[] items;

    private final BeanPropertyCompound compound;

    public BindableCompound(BeanPropertyCompound embProp, List<Bindable> list) {
        this.compound = embProp;
        this.items = list.toArray(new Bindable[list.size()]);
    }

    public String toString() {
        return "BindableCompound " + compound + " items:" + Arrays.toString(items);
    }

    public void dmlAppend(GenerateDmlRequest request) {

        for (int i = 0; i < items.length; i++) {
            items[i].dmlAppend(request);
        }
    }

    public void addToUpdate(PersistRequestBean<?> request, List<Bindable> list) {
        if (request.isAddToUpdate(compound)) {
            list.add(this);
        }
    }

    public void dmlBind(BindableRequest bindRequest, EntityBean bean) throws SQLException {

      throw new RuntimeException("This is broken, need to break out the scalar values!!");
      
        //Object valueObject = compound.getValue(bean);
        //for (int i = 0; i < items.length; i++) {
        //    items[i].dmlBind(bindRequest, valueObject);
        //}
    }

}
