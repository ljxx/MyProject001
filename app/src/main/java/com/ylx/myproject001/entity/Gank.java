package com.ylx.myproject001.entity;

import com.ylx.myproject001.api.GankApi;
import com.ylx.myproject001.core.entity.BaseListEntity;
import com.ylx.myproject001.core.entity.HttpResult;
import com.ylx.myproject001.core.utils.CollectionUtils;
import com.ylx.myproject001.core.utils.helper.RxSchedulers;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

/**
 * Created by Weiss on 2017/1/20.
 */

public class Gank extends BaseListEntity {

    public String _id;

    public String createdAt;

    public String desc;

    public String publishedAt;

    public String source;

    public String type;

    public String url;

    public String imageUrl;

    public boolean used;

    public String who;

    public List<String> images;

    @Override
    public Observable<HttpResult<List<Gank>>> getPage(int page) {
        return GankApi.getInstance().service.getGankData(param.get("gank"), page)
                .zipWith(GankApi.getInstance().service.getGankData("福利", page),
                        (BiFunction<HttpResult<List<Gank>>, HttpResult<List<Gank>>, HttpResult<List<Gank>>>) (listHttpResult, listHttpResult2) -> {
                            HttpResult zipItems = new HttpResult();
                            Gank zipItem;
                            List<Gank> zipResults = new ArrayList<Gank>();

                            for (int i = 0; i < listHttpResult2.results.size(); i++) {
                                zipItem = new Gank();
                                Gank item = listHttpResult2.results.get(i);
                                Gank gankInfo = listHttpResult.results.get(i);
                                if(CollectionUtils.isEmpty(gankInfo.images)) {
                                    zipItem.imageUrl = item.url;
                                }else {
                                    zipItem.imageUrl =gankInfo.images.get(0);
                                }
                                zipItem.url = gankInfo.url;
                                zipItem.desc = gankInfo.desc;
                                zipItem.who = gankInfo.who;
                                zipResults.add(zipItem);
                            }
                            zipItems.results=zipResults;
                            return zipItems;
                        })
                .compose(RxSchedulers.io_main());
    }

}
