package com.valeriotor.iWanderBackend.model.core;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.valeriotor.iWanderBackend.model.VisibilityType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Entity
public class TravelPlan implements Comparable<TravelPlan>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "username")
    private AppUser user;
    private String name;
    @Enumerated
    @Column(columnDefinition = "smallint")
    private VisibilityType visibility;
    private LocalDate startDate;
    @OneToMany(mappedBy = "travelPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Day> days;
    @ElementCollection
    private List<String> imageUrls;


    public TravelPlan() {
        this(null, 0, "", null, LocalDate.now(), new ArrayList<>());
    }

    public TravelPlan(AppUser user, long id, String name, VisibilityType visibility, LocalDate startDate, List<Day> days) {
        this.user = user;
        this.id = id;
        this.name = name;
        this.visibility = visibility;
        this.startDate = startDate;
        this.days = new ArrayList<>(days);
        this.imageUrls = new ArrayList<>();
    }

    public TravelPlan(AppUser user, long id, String name, VisibilityType visibility, List<Day> days) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.visibility = visibility;
        startDate = days.isEmpty() ? LocalDate.of(1970, 1, 1) : days.get(0).getDate();
        this.days = new ArrayList<>(days);
        this.imageUrls = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VisibilityType getVisibility() {
        return visibility;
    }

    public void setVisibility(VisibilityType visibility) {
        this.visibility = visibility;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = new ArrayList<>(days);
        resetStartDate();
    }

    public void addDay(Day d) {
        days.add(d);
        resetStartDate();
    }

    public void addAllDays(Collection<Day> days) {
        this.days.addAll(days);
        resetStartDate();
    }

    private void resetStartDate() {
        startDate = days.isEmpty() ? LocalDate.of(1970, 1, 1) : days.get(0).getDate();
    }

    @Override
    public int compareTo(TravelPlan o) {
        int dateCompare = startDate.compareTo(o.startDate);
        return dateCompare == 0 ? Long.compare(id, o.getId()) : dateCompare;
    }

    @Override
    public String toString() {
        return "TravelPlan{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", visibility=" + visibility +
                ", startDate=" + startDate +
                ", days=" + days +
                '}';
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public void addImageUrl(String url){
        imageUrls.add(url);
    }

    public void removeImageUrl(String url){
        imageUrls.remove(url);
    }

}
