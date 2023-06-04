package com.example.quickstartapplication;

import org.junit.Test;

import static org.junit.Assert.*;

import android.annotation.SuppressLint;

import com.example.quickstartapplication.model.HomeModel;
import com.example.quickstartapplication.model.KnowledgeModel;
import com.example.quickstartapplication.network.bean.JsonRootBean;
import com.example.quickstartapplication.network.bean.NavigateJsonRootBean;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getHomePage(){
        HomeModel homeModel = new HomeModel();
        Disposable disposable =  homeModel.getHomeList(0).subscribe(new Consumer<JsonRootBean>() {
            @Override
            public void accept(JsonRootBean jsonRootBean) throws Throwable {
                System.out.println(jsonRootBean.toString());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                throwable.printStackTrace();
            }
        });

        while (true){}

    }

    @SuppressLint("CheckResult")
    @Test
    public void getNav(){
        KnowledgeModel knowledgeModel = new KnowledgeModel();
        knowledgeModel.getNavigateData().subscribe(new Consumer<NavigateJsonRootBean>() {
            @Override
            public void accept(NavigateJsonRootBean navigateJsonRootBean) throws Throwable {
                System.out.println(navigateJsonRootBean.toString());
            }
        });
        while (true){}
    }
}