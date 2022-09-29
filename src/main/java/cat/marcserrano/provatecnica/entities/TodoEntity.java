package cat.marcserrano.provatecnica.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name="todos")
public class TodoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	private boolean completed;
	
	@ManyToOne
	private UserEntity user;

	public TodoEntity(Integer id, String title, boolean completed, UserEntity user) {
		super();
		this.id = id;
		this.title = title;
		this.completed = completed;
		this.user = user;
	}
	
	public TodoEntity(String title, boolean completed, UserEntity user) {
		super();
		this.title = title;
		this.completed = completed;
		this.user = user;
	}
	
	public TodoEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TodoEntity other = (TodoEntity) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "TodoEntity [id=" + id + ", title=" + title + ", completed=" + completed + ", user=" + user + "]";
	}
}
