package com.demo.cook.ui.shop.cart;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.demo.basecode.ui.ToastyUtils;
import com.demo.baselib.design.BaseViewModel;
import com.demo.cook.R;
import com.demo.cook.base.event.BusEvent;
import com.demo.cook.base.http.HttpCallback;
import com.demo.cook.base.http.HttpConfig;
import com.demo.cook.base.local.Storage;
import com.demo.cook.ui.shop.model.HttpGoodsApi;
import com.demo.cook.ui.shop.model.data.ShoppingCart;
import com.demo.cook.ui.shop.model.data.ShoppingCartDetails;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartViewModel extends BaseViewModel {

    HttpGoodsApi goodsApi = HttpConfig.getHttpServe(HttpGoodsApi.class);

    public MutableLiveData<List<ShoppingCartDetails>> shoppingCartListData = new MutableLiveData<>();
    public void getMyCart(){
        showLoading(R.string.text_loading);
        goodsApi.queryShoppingCart(Storage.getUserInfo().getUsername()).enqueue(new HttpCallback<List<ShoppingCartDetails>>() {
            @Override
            public void onSuccess(List<ShoppingCartDetails> data) {
                shoppingCartListData.setValue(data);
                totalPrice();
            }

            @Override
            public void finallyCall() {
                super.finallyCall();
                closeLoading();
            }
        });
    }
    public MutableLiveData<Boolean> selectAllData=new MutableLiveData<>(false);

    public void checkSelectAll(){
        boolean isSelectAll=true;
        for (ShoppingCartDetails cartDetails:shoppingCartListData.getValue()){
            if(!cartDetails.isCheck()){
                isSelectAll=false;
            }
        }
        selectAllData.setValue(isSelectAll);
    }


    public MutableLiveData<Boolean> editAble=new MutableLiveData<>(false);

    public void editOrSave(){
        if(editAble.getValue()){
            editAble.postValue(false);
        }else {
            editAble.postValue(true);
        }
    }

    public MutableLiveData<String> priceTotalData = new MutableLiveData<>();
    public void totalPrice(){
        BigDecimal priceTotal = new BigDecimal("0");
        for (ShoppingCartDetails cartDetails:shoppingCartListData.getValue()){
            if(cartDetails.isCheck()){
                Log.e("itemTotalPrice","Price=="+cartDetails.getPrice()+";BuyCount=="+cartDetails.getBuyCount()+";itemTotalPrice"+(cartDetails.getPrice().multiply(new BigDecimal(cartDetails.getBuyCount()))));
                priceTotal=priceTotal.add(cartDetails.getPrice().multiply(new BigDecimal(cartDetails.getBuyCount())));
            }
        }
        Log.e("totalPrice",priceTotal.toString());
        priceTotalData.setValue(priceTotal.toString());
    }



    public void addCount(ShoppingCartDetails goods){
        ShoppingCart params = new ShoppingCart();
        params.setUsername(goods.getUsername());
        params.setGoodsId(goods.getGoodsId());
        params.setBuyCount(goods.getBuyCount()+1);
        showLoading(R.string.text_loading);
        goodsApi.updateCount(params).enqueue(new HttpCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                EventBus.getDefault().post(new BusEvent.ShoppingCartCount(data));
                goods.setBuyCount(goods.getBuyCount()+1);
                if (goods.isCheck()){
                    totalPrice();
                }
            }

            @Override
            public void finallyCall() {
                super.finallyCall();
                closeLoading();
            }
        });
    }

    public void decreaseCount(ShoppingCartDetails goods){
        ShoppingCart params = new ShoppingCart();
        params.setUsername(goods.getUsername());
        params.setGoodsId(goods.getGoodsId());
        params.setBuyCount(goods.getBuyCount()-1);
        showLoading(R.string.text_loading);
        goodsApi.updateCount(params).enqueue(new HttpCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                EventBus.getDefault().post(new BusEvent.ShoppingCartCount(data));
                goods.setBuyCount(goods.getBuyCount()-1);
                if (goods.isCheck()){
                    totalPrice();
                }
            }

            @Override
            public void finallyCall() {
                super.finallyCall();
                closeLoading();
            }
        });
    }

    public void selectAll(){
        selectAllData.setValue(!selectAllData.getValue());
        for (ShoppingCartDetails cartDetails:shoppingCartListData.getValue()){
            cartDetails.setCheck(selectAllData.getValue());
        }
       totalPrice();
    }


    public void deleteGoods(){
        StringBuffer goodsIds=new StringBuffer("");
        for (ShoppingCartDetails cartDetails:shoppingCartListData.getValue()){
            if(cartDetails.isCheck()){
                goodsIds.append(",").append(cartDetails.getGoodsId());
            }
        }
        showLoading(R.string.text_loading);
        goodsApi.deleteShoppingCart(Storage.getUserInfo().getUsername(),goodsIds.toString().substring(1)).enqueue(new HttpCallback<List<ShoppingCartDetails>>() {
            @Override
            public void onSuccess(List<ShoppingCartDetails> data) {
                int count = 0;
                for (ShoppingCartDetails item:data){
                    count+= item.getBuyCount();
                }
                EventBus.getDefault().post(new BusEvent.ShoppingCartCount(count));
                shoppingCartListData.setValue(data);
                totalPrice();
            }

            @Override
            public void finallyCall() {
                super.finallyCall();
                closeLoading();
            }
        });
    }


}
