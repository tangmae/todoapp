package todo.todoapp.entity;

import java.io.Serializable;
import java.util.Date;

import org.joda.time.DateTime;

public class Todo implements Serializable {
	
	private long todoId;
	private int projectId;
	private int userId;
	private String title;
	private String description;
	private Date assignDate;
	private Date dueDate;
	private boolean isDone;
	
	
	public long getTodoId() {
		return todoId;
	}
	public void setTodoId(long todoId) {
		this.todoId = todoId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
}
