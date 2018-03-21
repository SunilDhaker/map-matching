package com.graphhopper.matching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class PolylineTest {




  @Test
  public void test(){


    Double[][] list = new Double[][]{ {0d ,0d} , {26d , 71d} , {20d , 70d}};
    List<Double[]>  listA=Arrays.asList(list);

//    String pl = "ejysMm}hyD_Lo\\qL_]yR{i@wRgj@iHuSqHuSsSil@}AqCkAiBcZmc@uLgQ}NuTkBcDeSa_@}MaWy@wAe@m@q@k@iE}Ce@k@Ui@Ie@?i@~AgV?iKEeLF}@Lw@Rw@d@eAn@gA|HuIrm@mo@xS{SbG_GrLeMpFaGdFeGxOqPh@w@^s@\\iAZmBTyHb@kKr@uNL{Dn@qMDiBbA}St@mRjAiV\\cCh@kMf@cPlBo\\^aJBaAf@aJv@qN\\uCk@]aD_AaGiAwDm@aEi@kMsAmC_@gGu@uFeAaJuAqCa@aD_@i^iFqMeBcNqBoB]uA_@cBu@cBmAqHwGyNaMqDcDqNqLuP}NsHkGgCcCmD_EeEkFeBgCqEoHoCwEoA}BiBoEoBiEkEcJgHsOs@sA{Wkj@sByEi@uAk@wBMeAMyA?iAD}BNkH@gCIsAQ}@kGwRwAuDWMgEiLyEiN}AuCiHcJqG}IkOyPgEyF{CwEqPwQ}HqJeIuK{E}GcFsGqGaHaEyEqCwEqDeHiMsSc@k@yJmKkJqKgC_DsD_H}AmD{@gCk@qCa@iDWiIMwIk@{V@oFPsMf@sFDaAAaAsFo]oHyf@uA{I_BgFgR}d@qEsK{H{OoJgRUy@@eAFGJSRuAFEBOCGNgCfAwEBoAG]uAaCo@_Bm@gCGw@EgBI_@kAsCMg@Ge@LiAH_@N_@r@gAh@gARWlDuDLS`AR|@Bz@Cx@QvAq@v@QjK]rBM~AYnLqD|A_@|ASlRoBjJw@vB_@fA]bDoD`HcIzNqPpKeMdBkBlEwDbSuNlAyApIoM|DcF|[q`@bAqAr@wAz@oB~CiId@_Al@y@zIwGnAoAh@y@Vs@d@oCZoATk@Vi@dJgJvC}CbGyHlB{CzAoB|DyD`LuMfBmBlDwBvEeCd@i@Rg@bD_Mx@yEf@iCd@_Al@aAzHiIdEeEp@eAbRwk@";
    String pl = OnlinePolylineEncoder.pointsToPoly(listA);

    System.out.println(pl);


    System.out.println(new PolylineDecoder().decode(pl));
  }
}
