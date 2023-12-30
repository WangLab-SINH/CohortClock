/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.templateproject.fragment.news;

import android.text.Html;
import android.widget.TextView;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.adapter.base.delegate.SimpleDelegateAdapter;
import com.xuexiang.templateproject.adapter.entity.NewInfo;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.textview.ExpandableTextView;

import butterknife.BindView;

/**
 * 首页动态
 *
 * @author xuexiang
 * @since 2019-10-30 00:15
 */
@Page(name = "Questions",anim = CoreAnim.none)
public class QuestionFragment extends BaseFragment {
    @BindView(R.id.textView1)
    ExpandableTextView mExpandableTextView1;

    @BindView(R.id.textView2)
    ExpandableTextView mExpandableTextView2;
    @BindView(R.id.textView3)
    ExpandableTextView mExpandableTextView3;
    @BindView(R.id.textView4)
    ExpandableTextView mExpandableTextView4;
    @BindView(R.id.textView5)
    ExpandableTextView mExpandableTextView5;
    @BindView(R.id.textView6)
    ExpandableTextView mExpandableTextView6;
    @BindView(R.id.textView7)
    ExpandableTextView mExpandableTextView7;
    @BindView(R.id.textView8)
    ExpandableTextView mExpandableTextView8;
    @BindView(R.id.textView9)
    ExpandableTextView mExpandableTextView9;
    @BindView(R.id.textView10)
    ExpandableTextView mExpandableTextView10;





    private SimpleDelegateAdapter<NewInfo> mNewsAdapter;

    /**
     * @return 返回为 null意为不需要导航栏
     */
//    @Override
//    protected TitleBar initTitle() {
//        return null;
//    }

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_question;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {


        String str1 = "<p><B>1. What is biological rhythms?</B><br/>" +
                "Biological rhythms refer to the cyclical properties that appear in physiological and behavioral parameters of living organisms, such as humans. The behavior of most living organisms oscillates due to the rotation and revolution of the Earth. The transition from spring to autumn, the rise and fall of tides; the blooming and wilting of flowers, the transition from night to day; starting work at sunrise and resting at sunset... all of these are rhythmic phenomena in nature.</p>";
        String  str2 = "<p><B>2. What is the relationship between circadian rhythms and diet?</B><br/>" +
                "Many physiological functions of organs need to adapt to eating behaviors. For example, the intestine secretes digestive enzymes to promote the digestion and absorption of food, the liver secretes bile, and the pancreas secretes digestive enzymes. Food signals play an important role in the modulation of circadian clocks, and this modulation is significant for maintaining the synchronization of human metabolism and behavior. <br/>"+
                "In addition to the brain, circadian clocks also exist in other tissues such as the liver, pancreas, and gastrointestinal tract. These clocks are responsible for coordinating various functions within these organs. For example, the circadian clock in the liver plays a crucial role in maintaining internal homeostasis, primarily regulating the secretion of enzymes and metabolism. Disruption of the circadian rhythms in the liver can accelerate the development of liver diseases, including fatty liver, cholestasis, hepatitis, cirrhosis, and liver cancer. These diseases, in turn, can affect the circadian rhythms.</p>";
        String str3 = "<p><B>3. What are the principles of chronobiology that this app is based on?</B><br/>" +
                "Our eating patterns inherently exhibit obvious rhythmic patterns; both fasting and eating change the metabolic state of the body. Studies on the diurnal rhythm models in humans and animals show that restricting the time of energy intake without changing the type of diet can bring multiple physiological benefits. At the same time, regular sleep and exercise are also necessary for health. This app was developed based on these principles by combining it with artificial intelligence algorithms." +
                "</p>";
        String str4 = "<p><B>4. How much sleep do I need every day?</B><br/>" +
                "There are individual differences among people, but it is generally believed that adults need between 7 to 9 hours of sleep each day.</p>";
        String str5 = "<p><B>5. How much exercise should I be getting?</B><br/>" +
                "The typical recommendation for adults is a minimum of 150 minutes of moderate aerobic activity or at least 75 minutes of vigorous aerobic activity each week.</p>";
        String str6 = "<p><B>6. What is restrictive eating and what are its benefits?</B><br/>" +
                "Restrictive eating, first proposed in the 1970s, refers to the long-term strict control of food intake to reduce or maintain body weight, essentially limiting daily calorie intake. In today's society, due to the high availability of food, people are in a relatively obesity-prone environment. As a result, more and more people are adopting restrictive eating as a strategy for weight loss.<br/>" +
                "Benefits:Restrictive eating can reduce the intake of unnecessary energy in the body, lessen the burden on the body's major internal organs, and prevent the occurrence of metabolic disorders. It is an effective dietary intervention measure to prevent diabetes and cardiovascular diseases. Restrictive eating can also slow down the aging process and decrease the incidence of chronic diseases."+
                "</p>";
        String str7 = "<p><B>7. What is the relationship between restrictive eating and diseases, as well as aging?</B><br/>" +
                "Restrictive eating can modulate multiple pathways such as the insulin signaling pathway, TOR pathway, AMPK signal transduction pathway, and others. These pathways play a very important role in regulating various aspects of the body. Restrictive eating can improve insulin sensitivity, reduce fasting blood glucose and insulin concentrations, and prevent obesity, type 2 diabetes, hypertension, and chronic inflammation. It may also prevent the occurrence of tumors. <br/>" +
                "Numerous studies have found that restrictive eating is an effective nutritional intervention measure to prevent most aging-related diseases and has the effect of delaying aging.<br/>"+
                "</p>";
        String str8 = "<p><B>8. Is water considered food?</B><br/>" +
                "No, it is not. Water can provide the body with necessary minerals, maintain water balance, and help the body maintain acid-base balance. However, water does not provide the body with calories, so it is not considered food in this context. Similarly, light tea and most medications are also not considered as food.</p>";
        String str9 = "<p><B>9. Are beverages and coffee considered food?</B><br/>" +
                " Yes, they are. Even though beverages and coffee are primarily water-based, in addition to providing hydration, they contain varying amounts of sugars, acids, milk, various amino acids, vitamins, inorganic salts, fruit and vegetable juices, and other nutritional components in coffee and different beverages. These can provide energy to the body, so they are considered as part of the diet.</p>";
        String str10 = "<p><B>10. How often should I use this app?</B><br/>" +
                "It is recommended that you record your diet on the app daily. This will help you understand your habits better, which is beneficial for your health.</p>";

//
//        String str1 = "<p><B>1.什么是生物节律？</B><br/>" +
//                "生物节律是指生物体比如我们人类在生理、行为指标等方面出现的周期性特征，比如我们的体温，情绪，血压等都在以24小时为周期发生有规律的变化，我们的睡眠-觉醒行为也是一种常见的生物节律现象。</p>";
//        String  str2 = "<p><B>2.生物节律和饮食的关系是什么？</B><br/>" +
//                "很多器官和组织的生理功能都要与人的进食行为相适应，比如小肠分泌消化酶来促进食物的消化与吸收，肝脏分泌胆汁，胰腺分泌消化酶。食物信号是一种可以独立地调节身体生物钟的影响因子，这种调控对于维持人体代谢和行为的同步化具有重要的意义。生物钟在人体的消化器官里也会存在，比如肝脏、胰腺以及胃肠等部位。比如肝脏的生物钟对于维持肝脏内部的稳态具有重要作用，主要负责调节肝脏的酶的分泌和新陈代谢，肝脏中生物节律的紊乱，会影响饮食后的食物的营养的消化与吸收，严重会引发肝部相关疾病，比如脂肪肝、肝炎，甚至癌症。</p>";
//        String str3 = "<p><B>3.这个App基于的节律生物学原理是什么？</B><br/>" +
//                "人的饮食本身具有明显的节律性，空腹与进食都会改变人体的代谢状态。在人与动物中的昼夜节律模型的研究表明，在不改变饮食种类的情况下将能量摄入的时间限制在一定时间之内，可以带来多方面的生理益处。这个App是在这个原理并结合人工智能算法的基础上研制的。" +
//                "</p>";
//        String str4 = "<p><B>4.什么是限制性饮食，它的好处有哪些？</B><br/>" +
//                "限制性饮食最早在上世纪就已经被提出了, 是指通过控制进食的方式来减轻或维持体重。这种行为本质上是限制每日卡路里摄入量或卡路里摄入总量。由于美食越来越丰富，人们很容易摄入超出自身活动需要的热量，从而导致超重甚至过度肥胖。限制性饮食通常会被女性所采用以维持体重，并逐渐被更多的人用来降低体重或维持体重，以满足人们审美和健康生活的需要。" +
//                "好处： \n" +
//                "限制性饮食通过减少能量的摄入量来减轻机体主要内脏器官的负担，避免机体稳态的破坏和代谢紊乱的发生，可以有效地预防肥胖的发生，控制体重。此外，限制性饮食还可以降低糖尿病和心血管疾病的发病率，减缓衰老速度，有效地预防慢性疾病。" +
//                "</p>";
//        String str5 = "<p><B>5.什么是时间限制性饮食？</B><br/>" +
//                "时间限制性饮食，顾名思义，就是通过限制饮食的时间窗口来进行限制性饮食。时间限制性饮食旨在维持一致的进食和禁食的周期，来维持非常强劲的昼夜节律。时间限制性饮食可以人为的有意增加内脏器官的节律性调控，进而减少肥胖的发生，改善睡眠质量，减少系统性炎症，维持肠道的稳态，对人体的健康会产生十分积极的影响。" +
//                "</p>";
//        String str6 = "<p><B>6.限制性饮食和体重有什么关系？</B><br/>" +
//                "许多研究发现限制性饮食是一个减轻体重的有效方法。这其中有两方面的原因，一方面是进食的时间减少了，肝脏以及胃肠得到休息的时间延长；另一方面是因为身体摄入的总卡路里数也减少了。" +
//                "</p>";
//        String str7 = "<p><B>7.限制性饮食和疾病以及衰老有什么关系？</B><br/>" +
//                "限制性饮食会调动胰岛素信号通路、TOR通路，AMPK信号转导途径等多个通路，这些通路可能会相互作用，或者在调控人体反应机制的多个方面有着十分重要的作用，进而会对疾病的发生有着一定的影响。例如，限制性饮食可以通过改善胰岛素敏感性来降低空腹血糖和胰岛素浓度，对于Ⅱ型糖尿病，慢性炎症、肥胖症以及高血压等疾病具有预防作用。此外，限制性饮食对于减少各种慢性顽疾和肿瘤的发生具有积极的预防和治疗作用。\n" +
//                "根据多项研究和临床试验表明，限制性饮食还对大多数与衰老有关的多种疾病具有预防作用，即限制性饮食是一种具有延缓衰老作用的营养干预措施。因此从长远来看，限制性饮食对于促进健康，提高生活质量，延长寿命具有积极影响\n。</p>";
//        String str8 = "<p><B>8.水是饮食吗？</B><br/>" +
//                "不是。水可以为人体提供所需的矿物质，维持体液渗透压、保持人体内的水平衡，以及帮助人体维持酸碱平衡，但是水不能为人体提供卡路里，所以在这里将水不作为饮食来考虑。同样，淡茶以及大部分药物都不作为饮食来考虑。</p>";
//        String str9 = "<p><B>9.饮料，咖啡是饮食吗？</B><br/>" +
//                "是。饮料咖啡虽然是以水作为基本原料，但是饮料咖啡除给人提供水分外，咖啡和饮料中会含有一定量的碳水化合物、脂肪、维生素等成分，甚至还会含有各种氨基酸等，可以为人体提供生命活动所必需的能量，所以将他们作为饮食来考虑。</p>";
//        String str10 = "<p><B>10.我应该多久使用一次这个App？</B><br/>" +
//                "建议每天记录下您的饮食，这样的有利于了解自己的饮食规律，让我们的应用给出更准确的建议哦，更有利于您的健康。</p>";
        mExpandableTextView1.setText(Html.fromHtml(str1));
        mExpandableTextView1.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                //XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });
        mExpandableTextView2.setText(Html.fromHtml(str2));
        mExpandableTextView2.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                //XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });
        mExpandableTextView3.setText(Html.fromHtml(str3));
        mExpandableTextView3.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                //XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });
        mExpandableTextView4.setText(Html.fromHtml(str4));
        mExpandableTextView4.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                //XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });
        mExpandableTextView5.setText(Html.fromHtml(str5));
        mExpandableTextView5.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                //XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });
        mExpandableTextView6.setText(Html.fromHtml(str6));
        mExpandableTextView6.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                //XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });
        mExpandableTextView7.setText(Html.fromHtml(str7));
        mExpandableTextView7.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                //XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });
        mExpandableTextView8.setText(Html.fromHtml(str8));
        mExpandableTextView8.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                //XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });
        mExpandableTextView9.setText(Html.fromHtml(str9));
        mExpandableTextView9.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                //XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });
        mExpandableTextView10.setText(Html.fromHtml(str10));
        mExpandableTextView10.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                //XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });

    }

    @Override
    protected void initListeners() {


    }
}
