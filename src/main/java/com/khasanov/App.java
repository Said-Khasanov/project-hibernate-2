package com.khasanov;

import com.khasanov.config.SessionFactoryCreator;
import com.khasanov.dao.*;
import com.khasanov.entity.*;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;


public class App {
    private static final Session session = SessionFactoryCreator.getSession();
    public static final ActorDAO actorDAO = new ActorDAO(session);
    private static final AddressDAO addressDAO = new AddressDAO(session);
    public static final CategoryDAO categoryDAO = new CategoryDAO(session);
    private static final CityDAO cityDAO = new CityDAO(session);
    private static final CustomerDAO customerDAO = new CustomerDAO(session);
    public static final FilmDAO filmDAO = new FilmDAO(session);
    public static final FilmTextDAO filmTextDAO = new FilmTextDAO(session);
    public static final InventoryDAO inventoryDAO = new InventoryDAO(session);
    public static final LanguageDAO languageDAO = new LanguageDAO(session);
    public static final PaymentDAO paymentDAO = new PaymentDAO(session);
    private static final RentalDAO rentalDAO = new RentalDAO(session);
    private static final StoreDAO storeDAO = new StoreDAO(session);

    public static void main(String[] args) {
        try (session) {
            System.out.println("6. Создание нового покупателя");
            createCustomer();
            System.out.println();

            System.out.println("7. Cобытие «покупатель пошел и вернул ранее арендованный фильм»");
            customerReturnsRentalFilm();
            System.out.println();

            System.out.println("8. Событие «покупатель сходил в магазин и арендовал там инвентарь");
            customerRentInventory();
            System.out.println();

            System.out.println("9. Событие «сняли новый фильм, и он стал доступен для аренды»");
            newFilmBecomeAvailableForRent();
        }
    }

    public static void createCustomer() {
        System.out.println("\t6.1. Берём любой город");
        City city = cityDAO.getFirstByName("Moscow");
        System.out.println(city.getCity());

        System.out.println("\t6.2. Берём любой магазин");
        Store store = storeDAO.getById(1L);
        System.out.println(store.getAddress());

        Address address = Address.builder()
                .city(city)
                .address("1 Testovaya St")
                .phone("81234567890")
                .district("test district")
                .build();
        System.out.println("\t6.3. Создаём новый адрес");
        System.out.println(addressDAO.save(address));

        Customer customer = Customer.builder()
                .address(address)
                .store(store)
                .firstName("Alex")
                .lastName("Gray")
                .build();
        System.out.println("\t6.4. Создаём нового покупателя");
        System.out.println(customerDAO.save(customer));
    }

    public static void customerReturnsRentalFilm() {
        System.out.println("\t7.1. Берём любой арендованный фильм");
        Rental rental = rentalDAO.getFirstUnreturnedRental();
        System.out.println(rental);

        System.out.println("\t7.2. Устанавливаем время возврата и сохраняем");
        rental.setReturnDate(LocalDateTime.now());
        System.out.println(rentalDAO.save(rental));
    }

    public static void customerRentInventory() {
        System.out.println("\t8.1. Берём любого покупателя");
        Customer customer = customerDAO.getById(1L);
        System.out.println(customer);
        System.out.println("\t8.2. Берём любой фильм доступный для аренды");
        Film film = filmDAO.getFirstAvailableForRentFilm();
        System.out.println(film);
        System.out.println("\t8.3. Берём любой магазин");
        Store store = storeDAO.getById(1L);
        System.out.println(store);
        System.out.println("\t8.4. Берём продавца из магазина");
        Staff staff = store.getManager();
        System.out.println(staff);

        Inventory inventory = Inventory.builder()
                .film(film)
                .store(store)
                .build();
        System.out.println("\t8.5. Создаём инвентарь");
        System.out.println(inventoryDAO.save(inventory));

        Rental rental = Rental.builder()
                .rentalDate(LocalDateTime.now())
                .staff(staff)
                .inventory(inventory)
                .customer(customer)
                .build();
        System.out.println("\t8.6. Создаём аренду");
        System.out.println(rentalDAO.save(rental));

        Payment payment = Payment.builder()
                .customer(customer)
                .staff(staff)
                .rental(rental)
                .amount(BigDecimal.valueOf(59.50))
                .paymentDate(LocalDateTime.now())
                .build();
        System.out.println("\t8.7. Создаём платёж");
        System.out.println(paymentDAO.save(payment));
    }

    public static void newFilmBecomeAvailableForRent() {
        System.out.println("\t9.1 Выбираем любой язык для фильма");
        Language language = languageDAO.getById(1L);
        System.out.println(language);
        System.out.println("\t9.2 Выбираем любые категории фильма");
        List<Category> categories = categoryDAO.getItems(0, 5);
        System.out.println(categories);
        System.out.println("\t9.3 Выбираем актерский состав");
        List<Actor> actors = actorDAO.getItems(0, 10);
        System.out.println(actors);

        Film film = Film.builder()
                .title("New film")
                .releaseYear(2024)
                .categories(new HashSet<>(categories))
                .actors(new HashSet<>(actors))
                .language(language)
                .build();
        System.out.println("\t9.4 Создаём фильм доступный для аренды");
        System.out.println(filmDAO.save(film));

        FilmText filmText = FilmText.builder()
                .id(film.getId())
                .film(film)
                .title("New film text")
                .build();
        System.out.println("\t9.5 Заполняем дополнительную таблицу о фильме");
        System.out.println(filmTextDAO.save(filmText));
    }
}