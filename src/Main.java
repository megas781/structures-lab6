import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        //Экземпляр неотсортированного массива
        int[] unsortedArray = {10,7,1,4,6,35,25,5,6,8,4,5,12,};

        System.out.println("Не оттсортированный   :");
        System.out.println(Arrays.toString(unsortedArray));

        System.out.println("Отсортированный массив:");
        System.out.println(Arrays.toString(getSortQuickly(unsortedArray)));
    }

    //Публичный метод быстрой сортировки
    public static int[] getSortQuickly(int[] array) {

        //Создаем копию массива, с которой в дальнейшем будем работать
        int[] arrayWorkingCopy = array.clone();

        //Применяем к ней алгоритм быстрой сортировки
        quickSortApplyDivision(arrayWorkingCopy, 0, arrayWorkingCopy.length - 1);

        //Возвращаем значение
        return arrayWorkingCopy;
    }

    //Метод, с помощью которого метод quickSortDivide применяется к половинкам
    private static void quickSortApplyDivision(int array[], int firstIndex, int lastIndex) {
        if (firstIndex < lastIndex) {

            //Индекс опорного значения
            int baseIndex = quickSortDivide(array, firstIndex, lastIndex);

            //Применение quickSortDivide к разделенным половинкам
            quickSortApplyDivision(array, firstIndex, baseIndex);
            quickSortApplyDivision(array, baseIndex+1, lastIndex);
        }
    }

//    Функция, разделяющая массив на две части на основе опорного значения
    private static int quickSortDivide(int array[], int firstIndex, int lastIndex) {

        //Определяем опору
        int baseIndex = getBaseIndex(array, firstIndex, lastIndex);
        int base = array[baseIndex];

        //Помещаем опору в конец массива для удобства
        int temp = base;
        array[baseIndex] = array[lastIndex];
        array[lastIndex] = temp;

        //Стенка. Те, что меньше опоры - слева, что больше - справа.
        int wall = (firstIndex);

        System.out.println("before partiton: " + Arrays.toString(array));
        for (int j = firstIndex; j <= lastIndex - 1; j++) {
            System.out.println(j + "th            : " + Arrays.toString(array));
            if (array[j] < base) {

                //То, что меньше индекса, отправляется за "невидимую" стенку
                int swapTemp = array[wall];
                array[wall] = array[j];
                array[j] = swapTemp;

                wall++;
            }


        }

        //В конце на место стенки ставим опорное значение
        int swapTemp = array[wall];
        array[wall] = array[lastIndex];
        array[lastIndex] = swapTemp;

        System.out.println("after final swap: " + Arrays.toString(array));
        //Возвращаем индекс опорного значения
        return wall;
    }

//    Функция, возвращающая индекс наиболее среднего значения в массиве
    private static int getBaseIndex(int[] array, int firstIndex, int lastIndex) {
        //1. Находим среднее значение, чтобы потом подобрать медиану
        double average = 0;
        for (int i = firstIndex; i <= lastIndex; i++) {
            average += array[i];
        }
        average /= lastIndex - firstIndex + 1;
        System.out.println();
        System.out.println("average: " + average + "; edges: (" + firstIndex + ", " + lastIndex + ")");

        //2. Нужно найти наиболее приближенное к среднему значению
        int minDeltaIndex = firstIndex;
        double minDelta = Math.abs(average - array[minDeltaIndex]);

        //Проходим по n-1 элементам, чтобы найти число, дающее наименьшую дельту
        for (int i = firstIndex + 1; i <= lastIndex; i++) {
            if ((Math.abs(average - array[i])) < minDelta) {
                minDelta = Math.abs(average - array[i]);
                minDeltaIndex = i;
            }
        }
        return minDeltaIndex;
    }
}
