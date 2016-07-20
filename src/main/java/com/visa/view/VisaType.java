package com.visa.view;

import com.visa.entity.VisatypeEntity;
import com.visa.service.VisaTypeService;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named("visaType")
@Scope("request")
public class VisaType {


 //  private UIData table;
    @Resource
    private VisaTypeService service;

    private VisatypeEntity entity;
     private List<VisatypeEntity> visatypeEntityList;
    public VisaType() {
        entity = new VisatypeEntity();
    }

    /**
     * @return
     */
    public List<VisatypeEntity> getVisatypeEntityList() {
        return this.service.findAllVisatype();
    }

    public String addAction() {
        entity.setId(service.getKeyValue());
        this.service.add(entity);
        return "persisted";
    }

    public String updateAction() {

       /* FacesContext fc = FacesContext.getCurrentInstance();
        Map requestParams = fc.getExternalContext().getRequestParameterMap();
        int mapsize = requestParams.size();
        Iterator keyValue = requestParams.entrySet().iterator();
        VisatypeEntity visatype= service.findVisatype(id);
        for (int i = 0; i < mapsize; i++) {
            Map.Entry entry = (Map.Entry) keyValue.next();
            Object key = entry.getKey();
           *//* if (key.toString().toLowerCase().equalsIgnoreCase("id")) {
                visatype.setId(new Integer(entry.getValue().toString()).intValue());
            } else*//* if (key.toString().toLowerCase().contains("name")) {
                visatype.setType(entry.getValue().toString());
            } else if (key.toString().toLowerCase().contains("remarklist")) {
                visatype.setRemark(entry.getValue().toString());
            }
        }*/
        this.service.update(entity);
        return "updated";
    }

    public String editAction(VisatypeEntity visatypeEntity) {
        visatypeEntity.setEditable(true);
        return null;
    }

    public String deleteAction() {
        this.service.delete(getVisatypeEntity().getId());
        return "removed";
    }

/*    public UIData getTable() {
        return table;
    }

    public void setTable(UIData table) {
        this.table = table;
    }*/

    public VisatypeEntity getVisatypeEntity() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map requestParams = fc.getExternalContext().getRequestParameterMap();

        if (requestParams.containsKey("Id")) {
            String id = (String) requestParams.get("Id");
            entity= service.findVisatype(new Integer(id).intValue());
        } else {
            entity = new VisatypeEntity();
        }
        return entity;
    }

    public VisatypeEntity getEntity() {
        return entity;
    }

    public void setEntity(VisatypeEntity entity) {
        this.entity = entity;
    }

    boolean editable;

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
