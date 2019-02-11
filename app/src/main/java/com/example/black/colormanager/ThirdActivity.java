package com.example.black.colormanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    private List<MColor> mColors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initColors ();
        MColorAdapter adapter = new MColorAdapter(ThirdActivity.this, R.layout.color_item, mColors);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    private void initColors () {

        String colorNumber = "#f7acbc:#deab8a:#817936:#444693:#ef5b9c:#fedcbd:#7f7522:#2b4490:#feeeed:#f47920:#80752c:#2a5caa:#f05b72:#905a3d:#87843b:#224b8f:#f15b6c:#8f4b2e:#726930:#003a6c:#f8aba6:#87481f:#454926:#102b6a:#f69c9f:#5f3c23:#2e3a1f:#426ab3:#f58f98:#6b473c:#4d4f36:#46485f:#ca8687:#faa755:#b7ba6b:#4e72b8:#f391a9:#fab27b:#b2d235:#181d4b:#bd6758:#f58220:#5c7a29:#1a2933:#d71345:#843900:#bed742:#121a2a:#d64f44:#905d1d:#7fb80e:#0c212b:#d93a49:#8a5d19:#a3cf62:#6a6da9:#b3424a:#8c531b:#769149:#585eaa:#c76968:#826858:#6d8346:#494e8f:#bb505d:#64492b:#78a355:#afb4db:#987165:#ae6642:#abc88b:#9b95c9:#ac6767:#56452d:#74905d:#6950a1:#973c3f:#96582a:#cde6c7:#6f60aa:#b22c46:#705628:#1d953f:#867892:#a7324a:#4a3113:#77ac98:#918597:#aa363d:#412f1f:#007d65:#6f6d85:#ed1941:#845538:#84bf96:#594c6d:#f26522:#8e7437:#45b97c:#694d9f:#d2553d:#69541b:#225a1f:#6f599c:#b4534b:#d5c59f:#367459:#8552a1:#ef4136:#cd9a5b:#007947:#543044:#c63c26:#cd9a5b:#40835e:#63434f:#f3715c:#b36d41:#2b6447:#7d5886:#a7573b:#df9464:#005831:#401c44:#aa2116:#b76f40:#006c54:#472d56:#b64533:#ad8b3d:#375830:#45224a:#b54334:#dea32c:#274d3d:#411445:#853f04:#d1923f:#375830:#4b2f3d:#840228:#c88400:#27342b:#402e4c:#7a1723:#c37e00:#65c294:#c77eb5:#a03939:#c37e00:#73b9a2:#ea66a6:#8a2e3b:#e0861a:#72baa7:#f173ac:#8e453f:#ffce7b:#005344:#fffffb:#8f4b4a:#fcaf17:#122e29:#fffef9:#892f1b:#ba8448:#293047:#f6f5ec:#6b2c25:#896a45:#00ae9d:#d9d6c3:#733a31:#76624c:#508a88:#d1c7b7:#54211d:#6d5826:#70a19f:#f2eada:#78331e:#ffc20e:#50b7c1:#d3d7d4:#53261f:#fdb933:#00a6ac:#999d9c:#f15a22:#d3c6a6:#78cdd1:#a1a3a6:#b4533c:#c7a252:#008792:#9d9087:#84331f:#dec674:#94d6da:#8a8c8e:#f47a55:#b69968:#afdfe4:#74787c:#f15a22:#c1a173:#5e7c85:#7c8577:#f3704b:#dbce8f:#76becc:#72777b:#da765b:#ffd400:#90d7ec:#77787b:#c85d44:#ffd400:#009ad6:#4f5555:#ae5039:#ffe600:#145b7d:#6c4c49:#6a3427:#f0dc70:#11264f:#563624:#8f4b38:#fcf16e:#7bbfea:#3e4145:#8e3e1f:#decb00:#33a3dc:#3c3645:#f36c21:#cbc547:#228fbd:#464547:#b4532a:#6e6b41:#2468a2:#130c0e:#b7704f:#596032:#2570a1:#281f1d:#de773f:#525f42:#2585a6:#2f271d:#c99979:#5f5d46:#1b315e:#1d1626";
        String colorName = "鸨色;赤白橡;油色;绀桔梗;踯躅色;肌色;伽罗色;花色;桜色;橙色;青丹;瑠璃色;蔷薇色;灰茶;莺色;琉璃绀;韩红;茶色;利久色;绀色;珊瑚色;桦茶色;媚茶;青蓝;红梅色;枯茶;蓝海松茶;杜若色;桃色;焦茶;青钝;胜色;薄柿;柑子色;抹茶色;群青色;薄红梅;杏色;黄緑;铁绀;曙色;蜜柑色;苔色;蓝铁;红色;褐色;若草色;青褐;赤丹;路考茶;若緑;褐返;红赤;饴色;萌黄;藤纳戸;臙脂色;丁子色;苗色;桔梗色;真赭;丁子茶;草色;绀蓝;今様色;黄栌;柳色;藤色;梅染;土器色;若草色;藤紫;退红色;黄枯茶;松叶色;青紫;苏芳;狐色;白緑;菫色;茜色;黄橡;薄緑;鸠羽色;红;银煤竹;千草色;薄色;银朱;涅色;青緑;薄鼠;赤;胡桃色;浅緑;鸠羽鼠;朱色;香色;緑;菖蒲色;洗朱;国防色;草色;江戸紫;红桦色;练色;木贼色;紫;红绯;肉色;常盘色;灭紫;桦色;人色;緑青色;葡萄鼠;铅丹色;土色;千歳緑;古代紫;赭;小麦色;深緑;暗红;绯色;琥珀色;萌葱色;葡萄;丹;木兰色;青白橡;茄子绀;土;栀子色;革色;紫绀;焦香;朽叶;麹尘;浓色;真红;萱草色;仙斎茶;二蓝;绯;黄金;若竹色;菖蒲色;红海老茶;金色;青磁色;牡丹色;浅苏芳;金茶;青竹色;赤紫;鸢色;卵色;铁色;白;小豆色;山吹色;锖鼠;胡粉色;弁柄色;黄土色;铁御纳戸;生成色;栗梅;朽叶色;青緑;灰白;海老茶;空五倍子色;锖浅葱;石竹色;深绯;莺茶;水浅葱;象牙色;赤铜色;向日葵色;新桥色;乳白色;赤褐色;郁金色;浅葱色;薄钝;金赤;砂色;白群;银鼠;赤茶;芥子色;御纳戸色;茶鼠;赤锖色;淡黄;瓮覗;鼠色;黄丹;亜麻色;水色;薄墨色;赤橙;枯色;蓝鼠;利休鼠;柿色;鸟子色;秘色;铅色;肉桂色;黄色;空色;灰色;桦色;蒲公英色;青;钝色;炼瓦色;中黄;蓝色;煤竹色;锖色;刈安色;浓蓝;黒茶;桧皮色;黄檗色;勿忘草色;黒橡;栗色;緑黄色;露草色;浓鼠;黄赤;鶸色;缥色;墨;代赭;海松色;浅缥;黒;骆驼色;鶸茶;薄缥;黒铁;黄茶;山鸠色;薄花色;蝋色;洗柿;生壁色;绀青;紫黒";
        String[] colors = colorNumber.split(":");
        String[] colorNames = colorName.split(";");
        for (int i = 0; i < colors.length; i++) {
            MColor color = new MColor(colorNames[i] + ":" + colors[i]);
            mColors.add(color);
        }
    }
}