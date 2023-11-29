package cz.secda1.test1;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestUtils {

    /**
     * Vypište všechny obědy včetně polévky a nápoje podávané dnes (29.11.2023)
     *
     * @param items
     * @param todayDate
     * @return
     */
    public static List<LunchItem> todaysMenu(List<LunchItem> items, String todayDate) {

        return items.stream().filter(p -> p.getDate().equals(todayDate)).collect(Collectors.toList());
    }

    /**
     * Ze seznamu dnes nabízených jídel vypište pouze názvy jídel (name) použijte funkci map()
     *
     * @param items
     * @param todayDate
     * @return
     */
    public static List<String> todaysMenuFilterNames(List<LunchItem> items, String todayDate) {
        return items.stream().filter(l -> l.getDate().equals(todayDate)).map(LunchItem::getName).collect(Collectors.toList());
    }

    /**
     * Vypište všechny názvy (name) hlavních jídel (kind.equals(LunchItemKind.MAIN_DISH) seřazené podle abecedy
     *
     * @param items
     * @return
     */
    public static List<String> getAllMainDishesSorted(List<LunchItem> items) {
        return items.stream().filter(p -> p.getKind().equals(LunchItemKind.MAIN_DISH)).sorted(Comparator.comparing(LunchItem :: getName)).map(LunchItem :: getName).collect(Collectors.toList());
    }

    /**
     * Vypište všechna hlavní jídla bez alergenů. Vypište pouze názvy jídel (name)
     *
     * @param items
     * @return
     */
    public static List<String> getAllMainDishesWithoutAllergens(List<LunchItem> items) {
        return items.stream().filter(p -> p.getKind().equals(LunchItemKind.MAIN_DISH)).filter(l -> l.getAllergens().isEmpty()).map(LunchItem :: getName).collect(Collectors.toList());
    }

    /**
     * Vypište datumy kdy je jako nápoj podáván "Ovocný nápoj", nebo "Sirup"
     *
     * @param items
     * @return
     */
    public static List<String> getDatesWhenDrinkIsFruitDrinkOrSyrup(List<LunchItem> items) {
        return items.stream().filter(p -> p.getKind().equals(LunchItemKind.DRINK)).filter(l -> l.getName().contains("Ovocný nápoj") || l.getName().contains("Sirup")).map(LunchItem :: getDate).collect(Collectors.toList());
    }


    /**
     * Vypište všechna přihlášená jídla vypište druh jídla (menuOption) a název jídla (name)
     * formát: menuOption: name
     *
     * @param items
     * @return
     * @example: "oběd1: Vepřová plec na žampiónech, rýže"
     */
    public static List<String> getAllOrderedDishes(List<LunchItem> items) {
        return items.stream().filter(l -> l.isOrdered()).map(lunchItem -> String.format("%s: %s", lunchItem.getMenuOption(), lunchItem.getName())).collect(Collectors.toList());
    }

    /**
     * Vypište cenu všech objednaných jídel, nebo vraťte výchozí hodnotu O pokud je vložen prázdný list, nebo list neobsahující žádné z přihlášených jídel
     *
     * @param items
     * @return
     */
    public static Double getSumOfAllOrderedMeals(List<LunchItem> items) {
        return items.stream().filter(l -> l.isOrdered()).map(LunchItem::getPrice).reduce(Double::sum).orElse(0.0);
    }

    /**
     * Seskupte obědy do mapy (Map<String, List<LunchItem>>) kde klíčem bude datum výdeje jídla (date)
     * a hodnotou List jídel nabízených v daný den. Použijte funkci groupBy()
     *
     * @param items
     * @return
     */
    public static Map<String, List<LunchItem>> groupMealsByDate(List<LunchItem> items) {
      return items.stream().collect(Collectors.groupingBy(lunchItem -> lunchItem.getDate()));
    }

    /**
     * Seskupte obědy do Mapy podle jejich alergenů Map<List<Allergen>, List<LunchItem>> kdy klíčem bude list obsažených alergenů
     * a hodontou List jídel obashující dané alergeny.
     *
     * @param items
     * @return
     */
    public static Map<List<Allergen>, List<LunchItem>> groupMealsByAllergens(List<LunchItem> items) {
        return items.stream().collect(Collectors.groupingBy(LunchItem::getAllergens));
    }
}
