package com.visa.view;

import com.visa.entity.ProfessionEntity;
import com.visa.service.ProfessionService;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import java.util.Map;

@Named
@Scope("request")
public class Profession {


	private UIData table;

	@Resource
	private ProfessionService service;

	private ProfessionEntity entity;
	public Profession() {
		service = new ProfessionService();
		entity = new ProfessionEntity();
	}

	/**
	 *
	 * 
	 * @return
	 */
	public DataModel getAllProfession() {
		return new ListDataModel(this.service.findAllProfession());
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

	public String editAction(Profession profession) {
		profession.setEditable(true);
		return null;
	}
	public String deleteAction() {
		this.service.delete(Long.valueOf((Integer)entity.getId()));
		return "removed";
	}

	public UIData getTable() {
		return table;
	}

	public void setTable(UIData table) {
		this.table = table;
	}

	public ProfessionEntity getProfessionEntity() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map requestParams = fc.getExternalContext().getRequestParameterMap();

		if(requestParams.containsKey("Id")){
			String id = (String) requestParams.get("Id");
			return service.findProfession(new Integer(id));
		}
		else {
			entity = new ProfessionEntity();
		}
		return entity;
	}

	public void setProfessionEntity(ProfessionEntity entity) {
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
