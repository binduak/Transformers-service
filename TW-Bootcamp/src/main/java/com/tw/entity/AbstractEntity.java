package com.tw.entity;


import java.io.Serializable; 
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractEntity <T> implements Serializable{


	private static final long serialVersionUID = 1L;
	@Version
	protected Long version;
	protected T id;
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdDate", nullable = false)
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedDate", nullable = false)
	private Date updatedDate;

	public AbstractEntity (){
	}

	public AbstractEntity (T id){
		this.id=id;
	}

	
	@PrePersist
    public void onCreate() {
    updatedDate = createdDate = new Date();
    }

    @PreUpdate
    public void onUpdate() {
    updatedDate = new Date();
    }
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@SuppressWarnings("rawtypes")
	protected final boolean idEquals(Object o) {
		if (this == o) return true;
		if ((o == null) || getClass()!= o.getClass()) return false;
		final AbstractEntity that = (AbstractEntity) o;
		if (this.id !=null ? !this.id.equals(that.id): that.id != null) return false;
		if(this.version !=null  ? !this.version.equals(that.version) : that.version != null) return false;
		return true;
	}

	protected final int idHashCode() {
		int result = super.hashCode();
		result = 31* result + ( this.version != null ? this.version.hashCode(): 0);
		result = 31* result + ( this.id != null ? this.id.hashCode(): 0);
		return result;
	}

	@Override
	public boolean equals (final Object o){
		return id.equals(o);
	}

	/*@Override
	public int hashCode(){
		return id.hashCode();
	}*/

	//@Column(unique = true, nullable = false)
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AbstractEntity [version=" + version + ", id=" + id + "]";
	}

}
