import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入两行测试数据，单词用逗号隔开：");

        String firstWordList = sc.nextLine();
        String secondWordList = sc.nextLine();

        //调用findCommonWordsWithSpace查出共同的单词
        List<String> result = findCommonWordsWithSpace(firstWordList, secondWordList);
        //输出最终结果
        System.out.println(result.toString());

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //如果字符串中出现非字母或者连续逗号，则抛出运行时异常，异常信息：input not valid
        if (!isSpecialCharacters(firstWordList) || !isSpecialCharacters(secondWordList)) {
            throw new RuntimeException("input not valid");
        }

        List<String> firstList = java.util.Arrays.asList(firstWordList.split(","));
        List<String> secondList = java.util.Arrays.asList(secondWordList.split(","));
        List<String> same = new ArrayList<String>(firstList.size());
        //不区分大小写比较
        firstList.stream().forEach( E -> {
                    secondList.stream().forEach(a -> {
                        if (E.equalsIgnoreCase(a)) {
                            same.add(E);
                        }
                    });
                });

        //过滤器写法，不过得把字母变成大写，输出全是大写，也不太好吧！
        // List<String> resultList = (List)firstList.stream().filter(it->secondList.contains(it)).distinct().collect(Collectors.toList());
        // return resultList;
        //查询相同单词，按照字典顺序排序并去重
        List<String> resultList  = same.stream().distinct().sorted().collect(Collectors.toList());
        return resultList;
    }



    /**
     * 判读输入字符串中是否含有特殊字符
     *
     * @param s 输入字符串
     * @return true:不含有
     * false:含有
     */
    private static boolean isSpecialCharacters(String s) {
        Pattern p = Pattern.compile("^[A-Za-z]*([A-Za-z]+[,])*([A-Za-z]+)$");
        Matcher m = p.matcher(s);
        return m.matches();
    }
}