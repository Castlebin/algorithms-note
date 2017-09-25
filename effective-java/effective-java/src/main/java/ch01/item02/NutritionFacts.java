package ch01.item02;

/**
 * Builder模式
 * 构建器模式
 */
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static class Builder implements ObjectBuilder<NutritionFacts> {
        private int servingSize = 0;
        private int servings = 0;
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder servingSize(int servingSize) {
            this.servingSize = servingSize;
            return this;
        }

        public Builder servings(int servings) {
            this.servings = servings;
            return this;
        }

        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder fat(int fat) {
            this.fat = fat;
            return this;
        }

        public Builder sodium(int sodium) {
            this.sodium = sodium;
            return this;
        }

        public Builder carbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        @Override
        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

}
