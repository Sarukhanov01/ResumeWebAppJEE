package dao.inter;

import entity.Country;

import java.util.List;

public interface CountryDaoInter {
    List<Country> getAll();
    public Country getById(int userId);
    boolean updateCountry(Country u);
    boolean removeCountry(int id);
}
