package fr.doandgo.gestionrh.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class AbstractFacadeController<Dto> {
    public abstract List<Dto> getAll();
    public abstract ResponseEntity<Dto> getById(Integer id);
    public abstract void create(Dto dto);
    public abstract void deleteById(Integer id);
    public abstract void update(Dto dto);
}
