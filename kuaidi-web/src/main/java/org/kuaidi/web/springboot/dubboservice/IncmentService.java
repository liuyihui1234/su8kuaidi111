package org.kuaidi.web.springboot.dubboservice;

import net.sf.json.JSONObject;
import com.alibaba.dubbo.config.annotation.Reference;
import net.sf.json.JSONArray;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesPostStation;
import org.kuaidi.bean.vo.*;
import org.kuaidi.iservice.IEforcesIncmentService;
import org.kuaidi.iservice.IEforcesPostStationService;
import org.kuaidi.utils.CalulateTwoLanLon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class IncmentService {
    @Reference(version = "1.0.0")
    IEforcesIncmentService iEforcesIncmentService;

    @Reference(version = "1.0.0")
    IEforcesPostStationService stationService;

    /**
     * 将number作为parentid查找该公司下的所有的下级公司
     * @param number
     * @return
     */
    public ResultVo findIncmentByParentid(String number){
        try {
            List<EforcesIncment>  totalIncment = new ArrayList<EforcesIncment>();
            if(StringUtils.isNotEmpty(number)){
                List<String> parentidList = new ArrayList<String>();
                String[] numbers = number.split(",");
                if(numbers.length > 0){
                    for(int i = 0 ; i < numbers.length ; i++) {
                        parentidList.add(numbers[i]);
                    }
                }
                List<EforcesIncment> selfList = iEforcesIncmentService.selectByNumber(parentidList);
                totalIncment.addAll(selfList);
                while(parentidList.size() > 0){
                    List<EforcesIncment> incmentList = iEforcesIncmentService.selectByParentid(parentidList);
                    if(incmentList != null && incmentList.size() > 0){
                        totalIncment.addAll(incmentList);
                        parentidList.clear();
                        for(int i = 0;i < incmentList.size();i++){
                            EforcesIncment  incMent = incmentList.get(i);
                            if(incMent != null && StringUtils.isNotEmpty(incMent.getNumber())){
                                parentidList.add(incMent.getNumber());
                            }
                        }
                    }else {
                    	parentidList.clear();
                    }
                }
                return ResultUtil.exec(true,"查询成功",totalIncment);
            }
            return ResultUtil.exec(false,"未查询到数据",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"查询失败",null);
        }
    }

    /**
     * 根据number查找该公司的详细信息
     * @param number
     * @return
     */
    public ResultVo findIncmentByNumber(String number){
        try {
            if(StringUtils.isNotEmpty(number)){
                List<String> numberList = new ArrayList<String>();
                String[] numbers = number.split(",");
                if(numbers.length > 0){
                    for(int i = 0 ; i < numbers.length ; i++) {
                        numberList.add(numbers[i]);
                    }
                }
                if(numberList.size() > 0){
                    List<EforcesIncment> incmentList = iEforcesIncmentService.selectByNumber(numberList);
                    if(incmentList != null && incmentList.size() > 0){
                        return ResultUtil.exec(true,"查询成功",incmentList);
                    }
                }
            }
            return ResultUtil.exec(false,"未查询到数据",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"查询失败",null);
        }
    }


    public ResultVo findIncmentPostStationByNumbers(String incNumbers, float longtitude , float latitude){
        try {
            List<EforcesIncment>  totalIncment = new ArrayList<EforcesIncment>();
            if(StringUtils.isNotEmpty(incNumbers)){
                List<String> parentidList = new ArrayList<String>();
                String[] numbers = incNumbers.split(",");
                if(numbers.length > 0){
                    for(int i = 0 ; i < numbers.length ; i++) {
                        parentidList.add(numbers[i]);
                    }
                }
                while(parentidList.size() > 0){
                    List<EforcesIncment> incmentList = iEforcesIncmentService.selectByParentid(parentidList);
                    if(incmentList != null && incmentList.size() > 0){
                    	totalIncment.clear();
                        totalIncment.addAll(incmentList);
                        parentidList.clear();
                        for(int i = 0;i < incmentList.size();i++){
                            EforcesIncment  incMent = incmentList.get(i);
                            if(incMent != null && StringUtils.isNotEmpty(incMent.getNumber())){
                                parentidList.add(incMent.getNumber());
                            }
                        }
                    }else {
                    	parentidList.clear();
                    }
                }
            }
            List<String> incNumberList = new ArrayList<String>();
            if(totalIncment.size() == 0 ){
                String[] numbers = incNumbers.split(",");
                if(numbers.length > 0){
                    for(int i = 0 ; i < numbers.length ; i++) {
                    	if(numbers[i] != null) {
                    		incNumberList.add(numbers[i]);
                    	}
                    }
                }
            }else {
            	for(int i = 0 ; i < totalIncment.size() ; i++) {
            		EforcesIncment  incment = totalIncment.get(i);
            		if(incment != null && incment.getNumber() != null ) {
            			incNumberList.add(incment.getNumber());
            		}
            	}
            }
            if(incNumberList.size() > 0 ) {
            	List <EforcesPostStation> postStationList =  stationService.getPostStationByincNumbers(incNumberList, longtitude , latitude);
            	//计算两经纬度之间距离
                for(int i=0; i < postStationList.size(); i++){
                    EforcesPostStation resultDay= postStationList.get(i);
                    //纬度
                    double lat2 = resultDay.getLatitude();
                    //经度
                    double lng2 = resultDay.getLongtitude();
                    //调用工具类计算两经纬度之间距离
                   String  calculateResult = CalulateTwoLanLon.getDistance(longtitude,latitude,lat2,lng2);
//                   String calculateCode = Double.toString(calculateResult);
                    resultDay.setDistance(calculateResult);
                }
            	return ResultUtil.exec(true,"查询驿站信息成功！",postStationList);
            }else {
            	return ResultUtil.exec(false,"参数错误，请确定！",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"查询失败",null);
        }
    }

          
    public PageVo getAllIncment(QueryPageVo page){
        try {
            PageInfo<EforcesIncment> info = iEforcesIncmentService.selectAllIcrment(page.getPage(),page.getLimit(),page.getInfo1());
            return ResultUtil.exec(info.getPageNum(),info.getSize(),info.getTotal(), info.getList());
        } catch (Exception e) {
            return ResultUtil.exec(1, 10 ,0, null);
        }
    }

    public ResultVo getIncmentById(Integer id){
        try {
            EforcesIncment eforcesIncment = iEforcesIncmentService.selectByPrimaryKey(id);
            return ResultUtil.exec(true,"ok",eforcesIncment);
        } catch (Exception e) {
            return ResultUtil.exec(false,"操作失败",null);
        }
    }

    public ResultVo saveIncment(EforcesIncment incment) {
        try {
            int insert = iEforcesIncmentService.insert(incment);
            return ResultUtil.exec(true, "操作成功", null);
        } catch (Exception e) {
            return ResultUtil.exec(false, "操作失败", null);
        }
    }

    public ResultVo updateIncment(EforcesIncment incment) {
        try {
            int insert = iEforcesIncmentService.updateByPrimaryKey(incment);
            return ResultUtil.exec(true, "添加成功", null);
        } catch (Exception e) {
            return ResultUtil.exec(false, "操作失败", null);
        }
    }

    public ProvinceVo getProvinId(String province, String city, String area) {
        ProvinceVo pro = new ProvinceVo();
        try {
            if (province != null) {
                pro.setProvincecode(iEforcesIncmentService.getprovinceid(province));
            } else {
                pro.setProvincecode("");
            }
            if (city != null) {
                pro.setCitycode(iEforcesIncmentService.getcityid(city));
            } else {
                pro.setCitycode("");
            }
            if (area != null) {
                if(city=="市辖区"){
                    city=pro.getProvincecode();
                }

                pro.setAreacode(iEforcesIncmentService.getareaid(area,city));
            } else {
                pro.setAreacode("");
            }
            return pro;
        } catch (Exception e) {
        }
        return pro;
    }



    /*
            * 统计网点信息
     */
	public ResultVo statisticsIncmentByNumber(String number) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(number)) {
			return ResultUtil.exec(false,"参数错误，订单不能为空！",null);
		}

		List<String> numList  = new ArrayList<String>();
		String[] numbers = number.split(",");
        if(numbers.length > 0){
            for(int i = 0 ; i < numbers.length ; i++) {
            	numList.add(numbers[i]);
            }
        }
        try {
        	List<Map<String, Object>> rstList = iEforcesIncmentService.statisticsByNumber(numList);
    		return ResultUtil.exec(true, "操作成功", rstList) ;
        }catch(Exception e) {
        	e.printStackTrace();
        	return ResultUtil.exec(false, "操作失败", null) ;
        }

	}

	/*
	 * 根据父节点查询 ，统计网点信息的
	 */
	public ResultVo statisticsIncByParentNumber(String parentNumber) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(parentNumber)) {
			return ResultUtil.exec(false,"参数错误，订单不能为空！",null);
		}

		List<String> parentidList = new ArrayList<String>();
		String[] numbers = parentNumber.split(",");
        if(numbers.length > 0){
            for(int i = 0 ; i < numbers.length ; i++) {
                parentidList.add(numbers[i]);
            }
        }
        try {
        	List<Map<String, Object>> rstList = iEforcesIncmentService.statisticsByParentid(parentidList);
        	return ResultUtil.exec(true, "操作成功", rstList) ;
        }catch(Exception e ) {
        	e.printStackTrace();
        	return ResultUtil.exec(false, "操作失败", null) ;
        }


	}


	/*
	     * 根据父节点的number， 查询其子节点的所有的数据
	*/
	public ResultVo findIncmentByParentNumber(String number){
		try {
		    if(StringUtils.isNotEmpty(number)){
		        List<String> parentidList = new ArrayList<String>();
		        String[] numbers = number.split(",");
		        if(numbers.length > 0){
		            for(int i = 0 ; i < numbers.length ; i++) {
		                parentidList.add(numbers[i]);
		            }
		        }
		        List<EforcesIncment> incmentList = iEforcesIncmentService.selectByParentid(parentidList);
		        if(incmentList != null && incmentList.size() > 0 ) {
		        	return ResultUtil.exec(true,"查询成功",incmentList);
		        }
		    }
		    return ResultUtil.exec(false,"未查询到数据",null);
		} catch (Exception e) {
		    e.printStackTrace();
		    return ResultUtil.exec(false,"查询失败",null);
		}
	}




}
