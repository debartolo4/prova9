package models;
import enums.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author G5 lab group
 * this class is Envelope Model which saves the 
 * Object ,task and message fields to send to server.
 */
public class Envelope implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7171959639692110621L;
    
    /** the model that we send to server. */
	private List<Object> objList;
	
	/** the task that need to do in server. */
	private task type;
	
	/**  The status that return from server. */
	private Status st;
	
	/**
	 * 
	 * Empty Contractor - only create array list.
	 */
	public Envelope() {
		this.objList = new ArrayList<Object>();
	}

	
	/**
	 * Gets the type(task).
	 *
	 * @return the task
	 */
	public task getType() {
		return type;
	}
	
	/**
	 * Sets the type(task).
	 *
	 * @param set the new task
	 */
	public void setType(task type) {
		this.type = type;
	}

	/**
	 * Gets the obj list.
	 *
	 * @return the obj list
	 */
	public List<Object> getobjList() {
		return objList;
	}

	/**
	 * Sets the obj list.
	 *
	 * @param obj the new obj list
	 */
	public void setobjList(List<Object> obj) {
		this.objList = obj;
	}
	
	
	/**
	 * Addobj list.
	 *
	 * @param add an object to the obj list
	 */
	public void addobjList(Object obj)
	{
		this.objList.add(obj);
	}
	
	/**
	 * Gets the single object.
	 *
	 * @return the single object
	 */
	public Object getSingleObject()
	{
		return this.objList.get(0);
	}


	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Status getStatus() {
		return st;
	}


	/**
	 * Sets the status.
	 *
	 * @param st is the new status
	 */
	public void setStatus(Status st) {
		this.st = st;
	}


	
}
