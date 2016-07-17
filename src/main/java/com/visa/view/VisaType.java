package com.visa.view;

import com.visa.entity.VisatypeEntity;
import com.visa.service.VisaTypeService;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import java.util.Map;

@Named("visaType")
@Scope("request")
public class VisaType {


	private UIData table;

	@Resource
	private VisaTypeService service;

	private VisatypeEntity entity;
	public VisaType() {
		service = new VisaTypeService();
		entity = new VisatypeEntity();
	}

	/**
	 *
	 * 
	 * @return
	 */
	public DataModel getAllVisaType() {
		return new ListDataModel(this.service.findAllVisatype());
	}

	public String addAction() {
		entity.setId(service.getKeyValue());
		this.service.add(entity);
		return "persisted";
	}

	public String updateAction() {
		this.service.update(entity);
		return "updated";
	}

	public String editAction(VisaType profession) {
		profession.setEditable(true);
		return null;
	}
	public String deleteAction() {
		this.service.delete(entity.getId());
		return "removed";
	}

	public UIData getTable() {
		return table;
	}

	public void setTable(UIData table) {
		this.table = table;
	}

	public VisatypeEntity getVisatypeEntity() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map requestParams = fc.getExternalContext().getRequestParameterMap();

		if(requestParams.containsKey("Id")){
			String id = (String) requestParams.get("Id");
			return service.findVisatype(new Integer(id));
		}
		else {
			entity = new VisatypeEntity();
		}
		return entity;
	}

	public void setVisatypeEntity(VisatypeEntity entity) {
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
