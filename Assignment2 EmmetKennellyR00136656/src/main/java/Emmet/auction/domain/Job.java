package Emmet.auction.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    boolean isOpen;
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate startDate;

    private long jobStartedEpoch = System.currentTimeMillis();

    private LocalDate finishDate;
    @JsonIgnore
    @OneToMany(mappedBy = "job")
    List<Bid> bids;

    @JsonIgnore
    @ManyToOne
    User user;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public long getJobStartedEpoch() {
        return jobStartedEpoch;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public Job(String name, String description, User newUser, boolean isOpen) {
        super();
        this.name = name;
        this.description = description;
        this.user = newUser;
        this.isOpen = isOpen;
        this.startDate = LocalDate.now();
        this.finishDate = this.startDate.plusDays(20);
    }

    public Job(Integer id, String name, String description) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //    @ManyToMany(mappedBy = "job")
    //    private Set<User> users;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Job() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Job(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
        //this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public Set<User> getUsers() {
    //     return users;
    // }

    //  public void setUsers(Set<User> users) {
    //      this.users = users;
    //  }

    @Override
    public String toString() {
        return "Job [id=" + id + ", name=" + name + ", description=" + description + ", bids=" + bids + "]";
    }
}
