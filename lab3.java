class Room {
    // Клас приміщення
}

class Apartment extends Room {
    // Клас квартири, який є похідним від приміщення
}

class Office extends Room {
    // Клас офісу, який є похідним від приміщення
}

class Building {
    private Room[] rooms; // Масив для зберігання приміщень в будівлі

    public Building(Room[] rooms) {
        this.rooms = rooms;
    }

    // Метод для підрахунку кількості квартир у будівлі
    public int countApartments() {
        int count = 0;
        for (Room room : rooms) {
            if (room instanceof Apartment) {
                count++;
            }
        }
        return count;
    }

    // Метод для підрахунку кількості офісів у будівлі
    public int countOffices() {
        int count = 0;
        for (Room room : rooms) {
            if (room instanceof Office) {
                count++;
            }
        }
        return count;
    }
}

class Main {
    public static void main(String[] args) {
        // Створення кількох приміщень (квартир і офісів)
        Room[] rooms = new Room[5];
        rooms[0] = new Apartment();
        rooms[1] = new Office();
        rooms[2] = new Apartment();
        rooms[3] = new Apartment();
        rooms[4] = new Office();

        // Створення будівлі та передача в неї приміщень
        Building building = new Building(rooms);

        // Підрахунок кількості квартир і офісів у будівлі
        int apartmentCount = building.countApartments();
        int officeCount = building.countOffices();

        System.out.println("Кількість квартир: " + apartmentCount);
        System.out.println("Кількість офісів: " + officeCount);
    }
}
