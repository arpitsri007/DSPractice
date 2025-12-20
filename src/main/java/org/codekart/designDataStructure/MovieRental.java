package org.codekart.designDataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/*
    leetcode 1912 - Design Movie Rental System

    * Given n shops, each shop carries at most one copy of a movie moviei.
    * Each movie is given as a 2D integer array entries where entries[i] = [shopi, moviei, pricei] indicates that there is a copy of movie moviei at shop shopi with a rental price of pricei. Each shop carries at most one copy of a movie moviei.

    The system should support the following functions:

    Search: Finds the cheapest 5 shops that have an unrented copy of a given movie. The shops should be sorted by price in ascending order, and in case of a tie, the one with the smaller shopi should appear first. If there are less than 5 matching shops, then all of them should be returned. If no shop has an unrented copy, then an empty list should be returned.
    Rent: Rents an unrented copy of a given movie from a given shop.
    Drop: Drops off a previously rented copy of a given movie at a given shop.
    Report: Returns the cheapest 5 rented movies (possibly of the same movie ID) as a 2D list res where res[j] = [shopj, moviej] describes that the jth cheapest rented movie moviej was rented from the shop shopj. The movies in res should be sorted by price in ascending order, and in case of a tie, the one with the smaller shopj should appear first, and if there is still tie, the one with the smaller moviej should appear first. If there are fewer than 5 rented movies, then all of them should be returned. If no movies are currently being rented, then an empty list should be returned.
    
 */

public class MovieRental {

    private static class PriceShop implements Comparable<PriceShop> {
        int price;
        int shop;

        PriceShop(int price, int shop) {
            this.price = price;
            this.shop = shop;
        }

        @Override
        public int compareTo(PriceShop other) {
            if (price != other.price) {
                return Integer.compare(price, other.price);
            }
            return Integer.compare(shop, other.shop);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            PriceShop other = (PriceShop) obj;
            return price == other.price && shop == other.shop;
        }

        @Override
        public int hashCode() {
            return 31 * price + shop;
        }
    }

    private static class ShopPrice implements Comparable<ShopPrice> {
        int shop;
        int price;

        ShopPrice(int shop, int price) {
            this.shop = shop;
            this.price = price;
        }

        @Override
        public int compareTo(ShopPrice other) {
            if (shop != other.shop) {
                return Integer.compare(shop, other.shop);
            }
            return Integer.compare(price, other.price);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ShopPrice other = (ShopPrice) obj;
            return shop == other.shop && price == other.price;
        }

        @Override
        public int hashCode() {
            return 31 * shop + price;
        }
    }

    private static class RentedMovie implements Comparable<RentedMovie> {
        int price;
        int shop;
        int movie;

        RentedMovie(int price, int shop, int movie) {
            this.price = price;
            this.shop = shop;
            this.movie = movie;
        }

        @Override
        public int compareTo(RentedMovie other) {
            if (price != other.price) {
                return Integer.compare(price, other.price);
            }
            if (shop != other.shop) {
                return Integer.compare(shop, other.shop);
            }
            return Integer.compare(movie, other.movie);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            RentedMovie other = (RentedMovie) obj;
            return price == other.price && shop == other.shop && movie == other.movie;
        }

        @Override
        public int hashCode() {
            int result = price;
            result = 31 * result + shop;
            result = 31 * result + movie;
            return result;
        }
    }

    private final Map<Integer, TreeSet<PriceShop>> availableByPrice;
    private final Map<Integer, TreeSet<ShopPrice>> availableByShop;
    private final Map<Integer, Map<Integer, Integer>> priceLookup;
    private final TreeSet<RentedMovie> rented;
    public MovieRental(int n, int[][] entries) {
        availableByPrice = new HashMap<>();
        availableByShop = new HashMap<>();
        priceLookup = new HashMap<>();
        rented = new TreeSet<>();

        for (int[] entry : entries) {
            int shop = entry[0];
            int movie = entry[1];
            int price = entry[2];

            availableByPrice
                .computeIfAbsent(movie, id -> new TreeSet<>())
                .add(new PriceShop(price, shop));

            availableByShop
                .computeIfAbsent(movie, id -> new TreeSet<>())
                .add(new ShopPrice(shop, price));

            priceLookup
                .computeIfAbsent(movie, id -> new HashMap<>())
                .put(shop, price);
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> result = new ArrayList<>();

        TreeSet<PriceShop> candidates = availableByPrice.get(movie);
        if (candidates == null || candidates.isEmpty()) {
            return result;
        }

        int count = 0;
        for (PriceShop entry : candidates) {
            result.add(entry.shop);
            count++;
            if (count == 5) {
                break;
            }
        }
        return result;
    }

    public void rent(int shop, int movie) {
        Map<Integer, Integer> shopPrices = priceLookup.get(movie);
        if (shopPrices == null) {
            return;
        }
        Integer price = shopPrices.get(shop);
        if (price == null) {
            return;
        }

        TreeSet<PriceShop> byPrice = availableByPrice.get(movie);
        TreeSet<ShopPrice> byShop = availableByShop.get(movie);
        if (byPrice == null || byShop == null) {
            return;
        }

        PriceShop priceShop = new PriceShop(price, shop);
        ShopPrice shopPrice = new ShopPrice(shop, price);

        if (byPrice.remove(priceShop)) {
            byShop.remove(shopPrice);
            rented.add(new RentedMovie(price, shop, movie));
        }

    }

    public void drop(int shop, int movie) {
        Map<Integer, Integer> shopPrices = priceLookup.get(movie);
        if (shopPrices == null) {
            return;
        }
        Integer price = shopPrices.get(shop);
        if (price == null) {
            return;
        }

        RentedMovie rentedMovie = new RentedMovie(price, shop, movie);
        if (!rented.remove(rentedMovie)) {
            return;
        }

        availableByPrice
            .computeIfAbsent(movie, id -> new TreeSet<>())
            .add(new PriceShop(price, shop));

        availableByShop
            .computeIfAbsent(movie, id -> new TreeSet<>())
            .add(new ShopPrice(shop, price));
    }

    // cheapeast 5 rented movies sorted by price --> shop --> movie
    public List<List<Integer>> report() {
        List<List<Integer>> result = new ArrayList<>();
        if (rented.isEmpty()) {
            return result;
        }

        int count = 0;
        for (RentedMovie entry : rented) {
            result.add(new ArrayList<>(Arrays.asList(entry.shop, entry.movie)));
            count++;
            if (count == 5) {
                break;
            }
        }
        return result;
    }
}
