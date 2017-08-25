package com.javaprodev;

import com.javaprodev.actions.Construct;
import com.javaprodev.actions.ConstructorAction;
import com.javaprodev.actions.objectactions.InvokeMethodAction;
import com.javaprodev.actions.objectactions.UpdateFieldAction;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.javaprodev.DynamicUtils.updateProperties;

/**
 * Created by X on 8/24/2017.
 * 1. Utwórz klasê Person o prywatnych polach:
 * - int height;
 * - boolean adult;
 * - String firstName;
 * - String lastName;
 * Korzystaj¹c z mechanizmu refleksji wypisz nazwy wszystkich zadeklarowanych w klasie pól.
 * 2. Korzystaj¹c z konstruktora utwórz obiekt typu Person. Korzystaj¹c z mechanizmu refleksji ustaw wartoci pól w klasie odwo³uj¹c siê bezporednio do pól.
 * 3. Korzystaj¹c z mechanizmu refleksji wypisz wartoci wszystkich pól utworzonej wczeniej osoby, przy u¿yciu obiektów typu Method. Powtórz zadanie bez podawania "na sztywno" nazw getterów. Znajd wszystkie gettery w klasie, a nastêpnie je wywo³aj.
 * 4. Utwórz instancjê klasy Person korzystaj¹c z mechanizmu refleksji.
 * 5. Stwórz klasê abstrakcyjn¹ Action, która bêdzie opisem akcji.
 * Action:
 * - Class clazz (wraz z getterem i konstruktorem ustawiaj¹cym wartoc)
 * <p>
 * oraz klasy, które bêd¹ tak¹ klasê rozszerza³y (InvokeMethodAction, UpdateFieldAction, ConstructorAction). Stwórz klasy w taki sposób, aby mo¿na by³o nastêpnie u¿yæ takiego obiektu, do przeprowadzenia konkretnej operacji - wywo³ania metody na obiekcie, zmiany wartoci pola, b¹d utworzenia obiektu w zadany sposób.
 * InvokeMethodAction oraz UpdateFieldAction mog¹ rozszerzaæ klasê ObjectAction.
 * Stwórz obiekt typu ConstructorAction, który pos³u¿y do utworzenia doros³ego "Jana Kowalskiego" o wzrocie 170.
 * Stwórz obiekt typu UpdateFieldAction, który pos³u¿y do zmiany wzrostu kowalskiego na 180.
 * Stwórz obiekt typu InvokeMethodAction, który pos³u¿y do zmiany wzrostu kowalskiego na 160.
 * <p>
 * 6. Napisz metodê, która za parametr przyjmie obiekt typu Class. Nastêpnie wyszuka konstruktor tej klasy, który przyjmie najwiêcej parametrów. U¿ytkownik zostanie w konsoli kolejno wypytany o wartoci (mo¿emy to zasymulowaæ), które nastêpnie zostan¹ u¿yje do utworzenia i zwrócenia utworzonego za pomoc¹ konstruktora obiektu.
 * <p>
 * public <T> T construct(Class<T> clazz) {
 * ...
 * }
 * <p>
 * 6.5 Jeli parametr konstruktora ma adnotacjê @NamedArg("..."), to w zapytaniu o wartoæ przeka¿ parametr adnotacji.
 * <p>
 * 7. Stwórz adnotacjê DynamicProperty.
 * Stwórz klasê House o w³aciwociach:
 * - roomCount
 * - petsAllowed
 * - address
 * - smokingAllowed
 * - ownerName
 * - paintColor
 * - kitchenArea
 * - bathroomCount
 * <p>
 * Za³ó¿my, ¿e dom nie bêdzie przebudowywany. Dodaj adnotacjê DynamicProperty do w³aciwoci, które bêdzie mo¿na zmieniaæ (te, które nie bêd¹ zmuszaæ nas do remontu/przebudowania domu).
 * Analogicznie post¹p dla klasy:
 * Person o w³aciwociach:
 * - firstName
 * - lastName
 * - height
 * - weight
 * - smoker
 * - hairColor
 * - eyeColor
 * <p>
 * Nastêpnie napisz metodê:
 * updateProperties(Object object, HashMap<String, Object> properties)
 * która korzystaj¹c z refleksji zaktualizuje pola obiektu object zgodnie z wartociami zadanymi w mapie, ale tylko te z adnotacjami DynamicProperty. Metoda, w przypadku b³êdnych parametrów powinna wywietliæ w konsoli informacjê o tym, co nie zgadza siê, jeli chodzi o parametry.
 */
public class Main {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {

        // Korzystaj¹c z mechanizmu refleksji wypisz nazwy wszystkich zadeklarowanych w klasie pól.
        Field[] allFields = Person.class.getDeclaredFields();
        for (Field field : allFields) {
            if (Modifier.isPrivate(field.getModifiers())) {
                System.out.println(field.getName());
            }
        }

        //2. Korzystaj¹c z konstruktora utwórz obiekt typu Person.
        Person person = new Person(180, true, "X", "Y");

        // Korzystaj¹c z mechanizmu refleksji ustaw wartoci pól w klasie odwo³uj¹c siê bezporednio do pól.
        Field height = Person.class.getDeclaredField("height");
        height.setAccessible(true);
        height.set(person, 200);

        // 3. Korzystaj¹c z mechanizmu refleksji wypisz wartoci wszystkich pól utworzonej wczeniej osoby,
        // przy u¿yciu obiektów typu Method.


        System.out.println(Person.class.getDeclaredMethod("getHeight").invoke(person));
        System.out.println(Person.class.getDeclaredMethod("isAdult").invoke(person));
        System.out.println(Person.class.getDeclaredMethod("getFirstName").invoke(person));
        System.out.println(Person.class.getDeclaredMethod("getLastName").invoke(person));

        // Powtórz zadanie bez podawania "na sztywno" nazw getterów. Znajd wszystkie gettery w klasie, a nastêpnie je wywo³aj.
        List<Method> getters = getGetters(Person.class);
        for (Method getter : getters) {
            System.out.println(getter.invoke(person));
        }

        //4. Utwórz instancjê klasy Person korzystaj¹c z mechanizmu refleksji.
        Constructor<Person> personConstructor = Person.class.getConstructor(int.class, boolean.class, String.class, String.class);
        Person person2 = personConstructor.newInstance(200, true, "Jan", "Kowalski");


        // 5.
        // Stwórz obiekt typu ConstructorAction, który pos³u¿y do utworzenia doros³ego "Jana Kowalskiego" o wzrocie 170.
        List<Object> params = new ArrayList<>();
        params.add(170);
        params.add(true);
        params.add("Jan");
        params.add("Kowalski");

        ConstructorAction<Person> createJanKowalski = new ConstructorAction<>(Person.class, params);
        Person janKowalski = createJanKowalski.perform();
        System.out.println(janKowalski.toString());
        System.out.println("ConstructorAction: " + janKowalski.getHeight());

        //  Stwórz obiekt typu UpdateFieldAction, który pos³u¿y do zmiany wzrostu kowalskiego na 180.
        UpdateFieldAction updateHeight = new UpdateFieldAction(Person.class, "height", 180);
        updateHeight.perform(janKowalski);
        System.out.println("UpdateFieldAction: " + janKowalski.getHeight());


        // Stwórz obiekt typu InvokeMethodAction, który pos³u¿y do zmiany wzrostu kowalskiego na 160.
        InvokeMethodAction invokeUpdateHeight = new InvokeMethodAction(Person.class, "height", 160, int.class);
        invokeUpdateHeight.perform(janKowalski);
        System.out.println("InvokeUpdateHeight: " + janKowalski.getHeight());

        // * 6. Napisz metodê, która za parametr przyjmie obiekt typu Class. Nastêpnie wyszuka konstruktor tej klasy, który przyjmie najwiêcej parametrów.
        // U¿ytkownik zostanie w konsoli kolejno wypytany o wartoci (mo¿emy to zasymulowaæ), które nastêpnie zostan¹ u¿yje do utworzenia i zwrócenia utworzonego za pomoc¹ konstruktora obiektu.
        Construct<Person> construct = new Construct<>(Person.class);
       // Person constructor = Person.class.getConstructor(int.class,boolean.class, String.class,String.class).newInstance(params.toArray());

        Person personX = construct.create();
        System.out.println(personX.toString());

         //* 7. Stwórz adnotacjê DynamicProperty.
        House house = new House(5,6,7,"8 Street", true,true,"owner","whitepaint");
       // house.getFieldsWithAnnotations();

        HashMap<String, Object> houseUpdateMap = new HashMap<String, Object>();
        houseUpdateMap.put("petsAllowed", false);
        houseUpdateMap.put("smokingAllowed", false);
        houseUpdateMap.put("ownerName", "John");
        houseUpdateMap.put("paintColor", "Snow");

        updateProperties(house,houseUpdateMap);
        System.out.println(house.toString());



        //--------
        System.out.println();
        Person ppp = new Person(280,true,"Ogre","Lord");
        System.out.println(ppp.toString());

        HashMap<String, Object> personUpdateMap = new HashMap<String, Object>();
        personUpdateMap.put("adult", false);
        personUpdateMap.put("firstName", "rokokokoko");

        updateProperties(ppp,personUpdateMap);
        System.out.println(ppp.toString());


    }


    private static List<Method> getGetters(Class<Person> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> getters = new ArrayList<>();
        for (Method method : methods) {
            if (isGetter(method)) {
                getters.add(method);
            }
        }
        return getters;
    }

    private static boolean isGetter(Method method) {
        return !(!method.getName().startsWith("get") && !method.getName().startsWith("is")) && method.getParameterTypes().length == 0 && !void.class.equals(method.getReturnType());
    }
}
