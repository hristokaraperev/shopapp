package com.fmi.shopapp.util;

import com.fmi.shopapp.dao.*;
import com.fmi.shopapp.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInit implements CommandLineRunner {

    private final AddressDao addressDao;
    private final CategoryDao categoryDao;
    private final LocationDao locationDao;
    private final OrderDao orderDao;
    private final ProductDao productDao;
    private final SystemUserDao systemUserDao;

    public DataInit(AddressDao addressDao, CategoryDao categoryDao, LocationDao locationDao, OrderDao orderDao, ProductDao productDao, SystemUserDao systemUserDao) {
        this.addressDao = addressDao;
        this.categoryDao = categoryDao;
        this.locationDao = locationDao;
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.systemUserDao = systemUserDao;
    }

    @Override
    public void run(String... args) throws Exception {
        Address sofia = createAddress(true, "Bulgaria", "Sofia", "Carigradsko shose", "1", "1000");
        Address plovdiv = createAddress(true, "Bulgaria", "Plovidiv", "Car Boris Treti", "1", "1000");
        Address burgas = createAddress(true, "Bulgaria", "Burgas", "Ivan Shishmanov", "1", "1000");
        Address varna = createAddress(true, "Bulgaria", "Varna", "Pencho Vladigerov", "1", "1000");
        Address ruse = createAddress(true, "Bulgaria", "Ruse", "Stoyan Zagorchinov", "1", "1000");

        addressDao.save(sofia);
        addressDao.save(plovdiv);
        addressDao.save(burgas);
        addressDao.save(varna);
        addressDao.save(ruse);

        Category food = createCategory(true, "Food");
        Category beauty = createCategory(true, "Beauty");
        Category sports = createCategory(true, "Sports");
        Category garden = createCategory(true, "Garden");
        Category clothes = createCategory(true, "Clothes");

        categoryDao.save(food);
        categoryDao.save(beauty);
        categoryDao.save(sports);
        categoryDao.save(garden);
        categoryDao.save(clothes);

        locationDao.save(createLocation(true, sofia));
        locationDao.save(createLocation(true, plovdiv));
        locationDao.save(createLocation(true, burgas));
        locationDao.save(createLocation(true, varna));
        locationDao.save(createLocation(true, ruse));

        SystemUser admin = createSystemUser(true, "admin", "admin", true, null);
        SystemUser mod = createSystemUser(true, "mod", "mod", false, null);
        SystemUser user = createSystemUser(true, "user", "user", false, null);

        systemUserDao.save(admin);
        systemUserDao.save(mod);
        systemUserDao.save(user);

        Product apple = createProduct(true, "Apple", "Green Apple", 1.0, food);
        Product perfume = createProduct(true, "Perfume", "Men's Perfume", 1.0, beauty);
        Product ball = createProduct(true, "Ball", "Football", 1.0, sports);
        Product bench = createProduct(true, "Bench", "Swinging bench", 1.0, garden);
        Product hoodie = createProduct(true, "Hoodie", "Yellow hoodie", 1.0, clothes);

        Order one = createOrder(true, admin, List.of(apple, ball, bench));
        Order two = createOrder(true, mod, List.of(perfume, bench));
        Order three = createOrder(true, user, List.of(hoodie, apple));

        productDao.save(apple);
        productDao.save(perfume);
        productDao.save(ball);
        productDao.save(bench);
        productDao.save(hoodie);

//        admin.setOrders(List.of(one));
//        mod.setOrders(List.of(two));
//        user.setOrders(List.of(three));

        orderDao.save(one);
        orderDao.save(two);
        orderDao.save(three);

        systemUserDao.save(admin);
        systemUserDao.save(mod);
        systemUserDao.save(user);




    }

    private Address createAddress(Boolean status, String country, String city, String street, String number, String postCode) {
        Address address = new Address();
        address.setStatus(status);
        address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setNumber(number);
        address.setPostCode(postCode);
        return address;
    }

    private Category createCategory(Boolean status, String name) {
        Category category = new Category();
        category.setStatus(status);
        category.setName(name);
        return category;
    }

    private Location createLocation(Boolean status, Address address) {
        Location location = new Location();
        location.setStatus(status);
        location.setAddress(address);
        return location;
    }

    private Order createOrder(Boolean status, SystemUser customer, List<Product> products) {
        Order order = new Order();
        order.setStatus(status);
        order.setCustomer(customer);
        order.setProducts(products);
        return order;
    }

    private Product createProduct(Boolean status, String name, String description, Double price, Category category) {
        Product product = new Product();
        product.setStatus(status);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        return product;
    }

    private SystemUser createSystemUser(Boolean status, String username, String password, Boolean isEmployee, List<Order> orders) {
        SystemUser systemUser = new SystemUser();
        systemUser.setStatus(status);
        systemUser.setUsername(username);
        systemUser.setPassword(password);
        systemUser.setIsEmployee(isEmployee);
//        systemUser.setOrders(orders);
        return systemUser;
    }
}
