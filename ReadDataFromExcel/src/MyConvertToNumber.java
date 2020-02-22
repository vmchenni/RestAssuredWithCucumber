public class MyConvertToNumber {


    public static  double sConvertToNumber(String sValue){
        String sPrice1StringValue="";
        String s = sValue;
        String [] sArrayValue=s.split(",");
        for(int istart=0;istart<sArrayValue.length;istart++){
            sPrice1StringValue=sPrice1StringValue+""+sArrayValue[istart];
        }
        //System.out.println("New Array value is :-"+sPrice1StringValue);
        //System.out.println("String converted to number is:-"+Double.parseDouble(sPrice1StringValue));
        return Double.parseDouble(sPrice1StringValue);
    }
}
