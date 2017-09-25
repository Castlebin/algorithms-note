package ch01.item02;

public class Test {

    public static void main(String[] args) {
        NutritionFacts nutritionFacts = new NutritionFacts.Builder()
            .servings(100)
            .servingSize(200)
            .calories(250)
            .carbohydrate(100)
            .fat(50)
            .sodium(10)
            .build();
    }

}
