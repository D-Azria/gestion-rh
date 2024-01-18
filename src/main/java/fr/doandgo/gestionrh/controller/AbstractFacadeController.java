package fr.doandgo.gestionrh.controller;

import java.util.List;

public abstract class AbstractFacadeController<Entity, Dto> {
    public abstract List<Entity> getAll();
    public abstract Entity getById(Integer id);
    public abstract void create(Dto dto);
    public abstract void deleteById(Integer id);
    public abstract void update(Dto dto);
}
