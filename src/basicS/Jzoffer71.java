package basicS;

public class Jzoffer71 {

	//牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
	//同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
	//例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
	//正确的句子应该是“I am a student.”。
	//Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
	
	//先大反转，再每个小反转
	public String ReverseSentence(String str) {
        if(str == null || str.length() == 0){
            return str;
        }
        char[] arr = str.toCharArray();
        reverse(arr, 0, arr.length - 1);
        int start = 0, end = 0;
        int i = 0;
        while(i < arr.length){
            while(i < arr.length && arr[i] == ' '){
                i++;
            }
            if(i == arr.length){
                break;
            }
            start = i;
            while(i < arr.length && arr[i] != ' '){
                i++;
            }
            end = i - 1;
            reverse(arr, start, end);
        }
        return new String(arr);
    }
    
    public void reverse(char[] arr, int start, int end){
        while(start < end){
            char c = arr[start];
            arr[start] = arr[end];
            arr[end] = c;
            start++;end--;
        }
    }
}
