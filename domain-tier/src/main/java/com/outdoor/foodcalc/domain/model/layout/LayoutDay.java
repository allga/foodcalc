package com.outdoor.foodcalc.domain.model.layout;

import com.outdoor.foodcalc.domain.model.ComplexFoodEntity;
import com.outdoor.foodcalc.domain.model.IDomainEntity;
import com.outdoor.foodcalc.domain.model.IValueObject;
import com.outdoor.foodcalc.domain.model.meal.MealRef;
import com.outdoor.foodcalc.domain.model.product.ProductRef;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Day entity, that contains some meals and may be some additional products.
 * Day doesn't include dishes. Dishes should be included into meals.
 * F.e. Breakfast & Lunch (meals) & some nuts & sweets (products).
 *
 * @author Anton Borovyk
 */
public class LayoutDay extends ComplexFoodEntity implements IDomainEntity<LayoutDay> {

    private final long dayId;
    private LocalDate date;
    private String description;
    private List<MealRef> meals;
    private List<ProductRef> products;

    public LayoutDay(long dayId, LocalDate date, List<MealRef> meals, Collection<ProductRef> products) {
        this.dayId = dayId;
        this.date = date;
        this.meals = new ArrayList<>(meals);
        this.products = new ArrayList<>(products);
    }

    public long getDayId() {
        return dayId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MealRef> getMeals() {
        return Collections.unmodifiableList(meals);
    }

    public void setMeals(List<MealRef> meals) {
        this.meals = new ArrayList<>(meals);
    }

    public List<ProductRef> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void setProducts(List<ProductRef> products) {
        this.products = new ArrayList<>(products);
    }

    @Override
    public boolean sameIdentityAs(LayoutDay other) {
        return dayId == other.dayId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LayoutDay)) return false;

        LayoutDay layoutDay = (LayoutDay) o;

        if (dayId != layoutDay.dayId) return false;
        if (date != null ? !date.equals(layoutDay.date) : layoutDay.date != null) return false;
        if (!IValueObject.sameCollectionAs(meals, layoutDay.meals)) return false;
        return IValueObject.sameCollectionAs(products, layoutDay.products);

    }

    @Override
    public int hashCode() {
        int result = (int) (dayId ^ (dayId >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    /**
     * Combine all collection of different food entities to complex products collection.
     *
     * @return collection of fields products collection
     */
    @Override
    protected Collection<Collection<ProductRef>> getProductsCollections() {
        //collect all meals products & products to one list
        final List<Collection<ProductRef>> allProductsList = meals.stream().map(MealRef::getAllProducts).collect(toList());
        allProductsList.add(products);
        return allProductsList;
    }
}
