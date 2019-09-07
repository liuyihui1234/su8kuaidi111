package org.kuaidi.bean.trackingmore;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/8/21 13:55
 * @description TrackingMore系统获取多个运单号的物流信息，返回数据VO
 */
public class GetVo {

    /**
     * meta : {"code":200,"type":"Success","message":"Success"}
     * data : {"page":1,"limit":4,"total":"3","count":3,"items":[{"id":"9eee8f3e935e2b6c4767345e599bf673","tracking_number":"3502339401241","carrier_code":"bestex","status":"pickup","track_update":true,"created_at":"2019-08-20T14:56:55+08:00","updated_at":"2019-08-20T16:15:04+08:00","order_create_time":null,"customer_email":"","title":"","order_id":null,"comment":null,"customer_name":null,"archived":false,"original_country":"","itemTimeLength":79,"stayTimeLength":26,"service_code":null,"status_info":null,"substatus":null,"origin_info":{"ReferenceNumber":null,"ItemReceived":"2019-06-03 14:57:41","ItemDispatched":null,"DepartfromAirport":null,"ArrivalfromAbroad":null,"CustomsClearance":null,"DestinationArrived":null,"weblink":"http://www.800bestex.com/","phone":"+86 4009 565656","carrier_code":"bestex","trackinfo":[{"Date":"2019-07-25 22:02:05","StatusDescription":"吉林市【吉林昌邑区双吉镇】，【姜小晖/15688983811】正在派件","Details":"吉林市","checkpoint_status":"pickup"},{"Date":"2019-07-22 19:33:06","StatusDescription":"派件送达【 和驿的驿站助手（15915418079 高沙镇黄塘村）】入柜/入库","Details":"邵阳市","checkpoint_status":"pickup"},{"Date":"2019-07-11 16:30:54","StatusDescription":"客户已取件，提货点：【百世邻里 祥符区城关小辉超市（15688983811 04乡道大队门口）】","Details":"开封市","checkpoint_status":"transit"},{"Date":"2019-06-03 14:57:41","StatusDescription":"吉林市【吉林昌邑区双吉镇】，【沈金龙/13604464344】正在派件","Details":"吉林市","checkpoint_status":"pickup","ItemNode":"ItemReceived"}]},"lastEvent":"吉林市【吉林昌邑区双吉镇】，【姜小晖/15688983811】正在派件,吉林市,2019-07-25 22:02:05","lastUpdateTime":"2019-07-25 22:02:05"},{"id":"badc8f47c16c6b595ed8541d109dbbc2","tracking_number":"51837622007298","carrier_code":"bestex","status":"delivered","track_update":false,"created_at":"2019-08-19T09:51:00+08:00","updated_at":null,"order_create_time":null,"customer_email":"","title":"","order_id":null,"comment":null,"customer_name":null,"archived":false,"original_country":"","itemTimeLength":1,"stayTimeLength":13,"service_code":null,"status_info":null,"substatus":null,"origin_info":{"ReferenceNumber":null,"ItemReceived":"2019-08-05 18:42:58","ItemDispatched":null,"DepartfromAirport":null,"ArrivalfromAbroad":null,"CustomsClearance":null,"DestinationArrived":null,"weblink":"http://www.800bestex.com/","phone":"+86 4009 565656","carrier_code":"bestex","trackinfo":[{"Date":"2019-08-06 18:39:14","StatusDescription":"天津市【天津武清分部】，唯品会退货组 已签收","Details":"天津市","checkpoint_status":"delivered"},{"Date":"2019-08-06 08:42:02","StatusDescription":"天津市【天津武清分部】，【曹凯楠/18630958014】正在派件","Details":"天津市","checkpoint_status":"pickup"},{"Date":"2019-08-06 08:41:02","StatusDescription":"到天津市【天津武清分部】","Details":"天津市","checkpoint_status":"transit"},{"Date":"2019-08-06 05:12:32","StatusDescription":"天津市【天津转运中心】，正发往【天津武清分部】","Details":"天津市","checkpoint_status":"transit"},{"Date":"2019-08-06 04:51:22","StatusDescription":"到天津市【天津转运中心】","Details":"天津市","checkpoint_status":"transit"},{"Date":"2019-08-06 02:11:04","StatusDescription":"北京市【北京转运中心】，正发往【天津转运中心】","Details":"北京市","checkpoint_status":"transit"},{"Date":"2019-08-06 00:43:08","StatusDescription":"到北京市【北京转运中心】","Details":"北京市","checkpoint_status":"transit"},{"Date":"2019-08-05 21:08:13","StatusDescription":"到北京市【海淀大街集货点】","Details":"北京市","checkpoint_status":"transit"},{"Date":"2019-08-05 18:42:58","StatusDescription":"北京市【北京海淀区六部】，【付明明/13522952529】已揽收","Details":"北京市","checkpoint_status":"transit","ItemNode":"ItemReceived"}]},"lastEvent":"天津市【天津武清分部】，唯品会退货组 已签收,天津市,2019-08-06 18:39:14","lastUpdateTime":"2019-08-06 18:39:14"},{"id":"b7e5dcbfac1336d0da8db7e383f770eb","tracking_number":"350233940124","carrier_code":"bestex","status":"pickup","track_update":true,"created_at":"2019-07-24T16:02:14+08:00","updated_at":"2019-08-21T15:35:05+08:00","order_create_time":null,"customer_email":"","title":"","order_id":null,"comment":null,"customer_name":null,"archived":false,"original_country":"","itemTimeLength":80,"stayTimeLength":30,"service_code":null,"status_info":null,"substatus":null,"origin_info":{"ReferenceNumber":null,"ItemReceived":"2019-06-03 14:57:41","ItemDispatched":null,"DepartfromAirport":null,"ArrivalfromAbroad":null,"CustomsClearance":null,"DestinationArrived":null,"weblink":"http://www.800bestex.com/","phone":"+86 4009 565656","carrier_code":"bestex","trackinfo":[{"Date":"2019-07-22 19:33:06","StatusDescription":"派件送达【 和驿的驿站助手（15915418079 高沙镇黄塘村）】入柜/入库","Details":"邵阳市","checkpoint_status":"pickup"},{"Date":"2019-07-11 16:30:54","StatusDescription":"客户已取件，提货点：【百世邻里 祥符区城关小辉超市（15688983811 04乡道大队门口）】","Details":"开封市","checkpoint_status":"transit"},{"Date":"2019-06-03 14:57:41","StatusDescription":"吉林市【吉林昌邑区双吉镇】，【沈金龙/13604464344】正在派件","Details":"吉林市","checkpoint_status":"pickup","ItemNode":"ItemReceived"}]},"lastEvent":"派件送达【 和驿的驿站助手（15915418079 高沙镇黄塘村）】入柜/入库,邵阳市,2019-07-22 19:33:06","lastUpdateTime":"2019-07-22 19:33:06"}]}
     */

    private MetaBean meta;
    private DataBean data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * code : 200
         * type : Success
         * message : Success
         */

        private int code;
        private String type;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class DataBean {
        /**
         * page : 1
         * limit : 4
         * total : 3
         * count : 3
         * items : [{"id":"9eee8f3e935e2b6c4767345e599bf673","tracking_number":"3502339401241","carrier_code":"bestex","status":"pickup","track_update":true,"created_at":"2019-08-20T14:56:55+08:00","updated_at":"2019-08-20T16:15:04+08:00","order_create_time":null,"customer_email":"","title":"","order_id":null,"comment":null,"customer_name":null,"archived":false,"original_country":"","itemTimeLength":79,"stayTimeLength":26,"service_code":null,"status_info":null,"substatus":null,"origin_info":{"ReferenceNumber":null,"ItemReceived":"2019-06-03 14:57:41","ItemDispatched":null,"DepartfromAirport":null,"ArrivalfromAbroad":null,"CustomsClearance":null,"DestinationArrived":null,"weblink":"http://www.800bestex.com/","phone":"+86 4009 565656","carrier_code":"bestex","trackinfo":[{"Date":"2019-07-25 22:02:05","StatusDescription":"吉林市【吉林昌邑区双吉镇】，【姜小晖/15688983811】正在派件","Details":"吉林市","checkpoint_status":"pickup"},{"Date":"2019-07-22 19:33:06","StatusDescription":"派件送达【 和驿的驿站助手（15915418079 高沙镇黄塘村）】入柜/入库","Details":"邵阳市","checkpoint_status":"pickup"},{"Date":"2019-07-11 16:30:54","StatusDescription":"客户已取件，提货点：【百世邻里 祥符区城关小辉超市（15688983811 04乡道大队门口）】","Details":"开封市","checkpoint_status":"transit"},{"Date":"2019-06-03 14:57:41","StatusDescription":"吉林市【吉林昌邑区双吉镇】，【沈金龙/13604464344】正在派件","Details":"吉林市","checkpoint_status":"pickup","ItemNode":"ItemReceived"}]},"lastEvent":"吉林市【吉林昌邑区双吉镇】，【姜小晖/15688983811】正在派件,吉林市,2019-07-25 22:02:05","lastUpdateTime":"2019-07-25 22:02:05"},{"id":"badc8f47c16c6b595ed8541d109dbbc2","tracking_number":"51837622007298","carrier_code":"bestex","status":"delivered","track_update":false,"created_at":"2019-08-19T09:51:00+08:00","updated_at":null,"order_create_time":null,"customer_email":"","title":"","order_id":null,"comment":null,"customer_name":null,"archived":false,"original_country":"","itemTimeLength":1,"stayTimeLength":13,"service_code":null,"status_info":null,"substatus":null,"origin_info":{"ReferenceNumber":null,"ItemReceived":"2019-08-05 18:42:58","ItemDispatched":null,"DepartfromAirport":null,"ArrivalfromAbroad":null,"CustomsClearance":null,"DestinationArrived":null,"weblink":"http://www.800bestex.com/","phone":"+86 4009 565656","carrier_code":"bestex","trackinfo":[{"Date":"2019-08-06 18:39:14","StatusDescription":"天津市【天津武清分部】，唯品会退货组 已签收","Details":"天津市","checkpoint_status":"delivered"},{"Date":"2019-08-06 08:42:02","StatusDescription":"天津市【天津武清分部】，【曹凯楠/18630958014】正在派件","Details":"天津市","checkpoint_status":"pickup"},{"Date":"2019-08-06 08:41:02","StatusDescription":"到天津市【天津武清分部】","Details":"天津市","checkpoint_status":"transit"},{"Date":"2019-08-06 05:12:32","StatusDescription":"天津市【天津转运中心】，正发往【天津武清分部】","Details":"天津市","checkpoint_status":"transit"},{"Date":"2019-08-06 04:51:22","StatusDescription":"到天津市【天津转运中心】","Details":"天津市","checkpoint_status":"transit"},{"Date":"2019-08-06 02:11:04","StatusDescription":"北京市【北京转运中心】，正发往【天津转运中心】","Details":"北京市","checkpoint_status":"transit"},{"Date":"2019-08-06 00:43:08","StatusDescription":"到北京市【北京转运中心】","Details":"北京市","checkpoint_status":"transit"},{"Date":"2019-08-05 21:08:13","StatusDescription":"到北京市【海淀大街集货点】","Details":"北京市","checkpoint_status":"transit"},{"Date":"2019-08-05 18:42:58","StatusDescription":"北京市【北京海淀区六部】，【付明明/13522952529】已揽收","Details":"北京市","checkpoint_status":"transit","ItemNode":"ItemReceived"}]},"lastEvent":"天津市【天津武清分部】，唯品会退货组 已签收,天津市,2019-08-06 18:39:14","lastUpdateTime":"2019-08-06 18:39:14"},{"id":"b7e5dcbfac1336d0da8db7e383f770eb","tracking_number":"350233940124","carrier_code":"bestex","status":"pickup","track_update":true,"created_at":"2019-07-24T16:02:14+08:00","updated_at":"2019-08-21T15:35:05+08:00","order_create_time":null,"customer_email":"","title":"","order_id":null,"comment":null,"customer_name":null,"archived":false,"original_country":"","itemTimeLength":80,"stayTimeLength":30,"service_code":null,"status_info":null,"substatus":null,"origin_info":{"ReferenceNumber":null,"ItemReceived":"2019-06-03 14:57:41","ItemDispatched":null,"DepartfromAirport":null,"ArrivalfromAbroad":null,"CustomsClearance":null,"DestinationArrived":null,"weblink":"http://www.800bestex.com/","phone":"+86 4009 565656","carrier_code":"bestex","trackinfo":[{"Date":"2019-07-22 19:33:06","StatusDescription":"派件送达【 和驿的驿站助手（15915418079 高沙镇黄塘村）】入柜/入库","Details":"邵阳市","checkpoint_status":"pickup"},{"Date":"2019-07-11 16:30:54","StatusDescription":"客户已取件，提货点：【百世邻里 祥符区城关小辉超市（15688983811 04乡道大队门口）】","Details":"开封市","checkpoint_status":"transit"},{"Date":"2019-06-03 14:57:41","StatusDescription":"吉林市【吉林昌邑区双吉镇】，【沈金龙/13604464344】正在派件","Details":"吉林市","checkpoint_status":"pickup","ItemNode":"ItemReceived"}]},"lastEvent":"派件送达【 和驿的驿站助手（15915418079 高沙镇黄塘村）】入柜/入库,邵阳市,2019-07-22 19:33:06","lastUpdateTime":"2019-07-22 19:33:06"}]
         */

        private int page;
        private int limit;
        private String total;
        private int count;
        private List<ItemsBean> items;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * id : 9eee8f3e935e2b6c4767345e599bf673
             * tracking_number : 3502339401241
             * carrier_code : bestex
             * status : pickup
             * track_update : true
             * created_at : 2019-08-20T14:56:55+08:00
             * updated_at : 2019-08-20T16:15:04+08:00
             * order_create_time : null
             * customer_email :
             * title :
             * order_id : null
             * comment : null
             * customer_name : null
             * archived : false
             * original_country :
             * itemTimeLength : 79
             * stayTimeLength : 26
             * service_code : null
             * status_info : null
             * substatus : null
             * origin_info : {"ReferenceNumber":null,"ItemReceived":"2019-06-03 14:57:41","ItemDispatched":null,"DepartfromAirport":null,"ArrivalfromAbroad":null,"CustomsClearance":null,"DestinationArrived":null,"weblink":"http://www.800bestex.com/","phone":"+86 4009 565656","carrier_code":"bestex","trackinfo":[{"Date":"2019-07-25 22:02:05","StatusDescription":"吉林市【吉林昌邑区双吉镇】，【姜小晖/15688983811】正在派件","Details":"吉林市","checkpoint_status":"pickup"},{"Date":"2019-07-22 19:33:06","StatusDescription":"派件送达【 和驿的驿站助手（15915418079 高沙镇黄塘村）】入柜/入库","Details":"邵阳市","checkpoint_status":"pickup"},{"Date":"2019-07-11 16:30:54","StatusDescription":"客户已取件，提货点：【百世邻里 祥符区城关小辉超市（15688983811 04乡道大队门口）】","Details":"开封市","checkpoint_status":"transit"},{"Date":"2019-06-03 14:57:41","StatusDescription":"吉林市【吉林昌邑区双吉镇】，【沈金龙/13604464344】正在派件","Details":"吉林市","checkpoint_status":"pickup","ItemNode":"ItemReceived"}]}
             * lastEvent : 吉林市【吉林昌邑区双吉镇】，【姜小晖/15688983811】正在派件,吉林市,2019-07-25 22:02:05
             * lastUpdateTime : 2019-07-25 22:02:05
             */

            private String id;
            private String tracking_number;
            private String carrier_code;
            private String status;
            private boolean track_update;
            private String created_at;
            private String updated_at;
            private Object order_create_time;
            private String customer_email;
            private String title;
            private Object order_id;
            private Object comment;
            private Object customer_name;
            private boolean archived;
            private String original_country;
            private int itemTimeLength;
            private int stayTimeLength;
            private Object service_code;
            private Object status_info;
            private Object substatus;
            private OriginInfoBean origin_info;
            private String lastEvent;
            private String lastUpdateTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTracking_number() {
                return tracking_number;
            }

            public void setTracking_number(String tracking_number) {
                this.tracking_number = tracking_number;
            }

            public String getCarrier_code() {
                return carrier_code;
            }

            public void setCarrier_code(String carrier_code) {
                this.carrier_code = carrier_code;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public boolean isTrack_update() {
                return track_update;
            }

            public void setTrack_update(boolean track_update) {
                this.track_update = track_update;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public Object getOrder_create_time() {
                return order_create_time;
            }

            public void setOrder_create_time(Object order_create_time) {
                this.order_create_time = order_create_time;
            }

            public String getCustomer_email() {
                return customer_email;
            }

            public void setCustomer_email(String customer_email) {
                this.customer_email = customer_email;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getOrder_id() {
                return order_id;
            }

            public void setOrder_id(Object order_id) {
                this.order_id = order_id;
            }

            public Object getComment() {
                return comment;
            }

            public void setComment(Object comment) {
                this.comment = comment;
            }

            public Object getCustomer_name() {
                return customer_name;
            }

            public void setCustomer_name(Object customer_name) {
                this.customer_name = customer_name;
            }

            public boolean isArchived() {
                return archived;
            }

            public void setArchived(boolean archived) {
                this.archived = archived;
            }

            public String getOriginal_country() {
                return original_country;
            }

            public void setOriginal_country(String original_country) {
                this.original_country = original_country;
            }

            public int getItemTimeLength() {
                return itemTimeLength;
            }

            public void setItemTimeLength(int itemTimeLength) {
                this.itemTimeLength = itemTimeLength;
            }

            public int getStayTimeLength() {
                return stayTimeLength;
            }

            public void setStayTimeLength(int stayTimeLength) {
                this.stayTimeLength = stayTimeLength;
            }

            public Object getService_code() {
                return service_code;
            }

            public void setService_code(Object service_code) {
                this.service_code = service_code;
            }

            public Object getStatus_info() {
                return status_info;
            }

            public void setStatus_info(Object status_info) {
                this.status_info = status_info;
            }

            public Object getSubstatus() {
                return substatus;
            }

            public void setSubstatus(Object substatus) {
                this.substatus = substatus;
            }

            public OriginInfoBean getOrigin_info() {
                return origin_info;
            }

            public void setOrigin_info(OriginInfoBean origin_info) {
                this.origin_info = origin_info;
            }

            public String getLastEvent() {
                return lastEvent;
            }

            public void setLastEvent(String lastEvent) {
                this.lastEvent = lastEvent;
            }

            public String getLastUpdateTime() {
                return lastUpdateTime;
            }

            public void setLastUpdateTime(String lastUpdateTime) {
                this.lastUpdateTime = lastUpdateTime;
            }

            public static class OriginInfoBean {
                /**
                 * ReferenceNumber : null
                 * ItemReceived : 2019-06-03 14:57:41
                 * ItemDispatched : null
                 * DepartfromAirport : null
                 * ArrivalfromAbroad : null
                 * CustomsClearance : null
                 * DestinationArrived : null
                 * weblink : http://www.800bestex.com/
                 * phone : +86 4009 565656
                 * carrier_code : bestex
                 * trackinfo : [{"Date":"2019-07-25 22:02:05","StatusDescription":"吉林市【吉林昌邑区双吉镇】，【姜小晖/15688983811】正在派件","Details":"吉林市","checkpoint_status":"pickup"},{"Date":"2019-07-22 19:33:06","StatusDescription":"派件送达【 和驿的驿站助手（15915418079 高沙镇黄塘村）】入柜/入库","Details":"邵阳市","checkpoint_status":"pickup"},{"Date":"2019-07-11 16:30:54","StatusDescription":"客户已取件，提货点：【百世邻里 祥符区城关小辉超市（15688983811 04乡道大队门口）】","Details":"开封市","checkpoint_status":"transit"},{"Date":"2019-06-03 14:57:41","StatusDescription":"吉林市【吉林昌邑区双吉镇】，【沈金龙/13604464344】正在派件","Details":"吉林市","checkpoint_status":"pickup","ItemNode":"ItemReceived"}]
                 */

                private Object ReferenceNumber;
                private String ItemReceived;
                private Object ItemDispatched;
                private Object DepartfromAirport;
                private Object ArrivalfromAbroad;
                private Object CustomsClearance;
                private Object DestinationArrived;
                private String weblink;
                private String phone;
                private String carrier_code;
                private List<TrackinfoBean> trackinfo;

                public Object getReferenceNumber() {
                    return ReferenceNumber;
                }

                public void setReferenceNumber(Object ReferenceNumber) {
                    this.ReferenceNumber = ReferenceNumber;
                }

                public String getItemReceived() {
                    return ItemReceived;
                }

                public void setItemReceived(String ItemReceived) {
                    this.ItemReceived = ItemReceived;
                }

                public Object getItemDispatched() {
                    return ItemDispatched;
                }

                public void setItemDispatched(Object ItemDispatched) {
                    this.ItemDispatched = ItemDispatched;
                }

                public Object getDepartfromAirport() {
                    return DepartfromAirport;
                }

                public void setDepartfromAirport(Object DepartfromAirport) {
                    this.DepartfromAirport = DepartfromAirport;
                }

                public Object getArrivalfromAbroad() {
                    return ArrivalfromAbroad;
                }

                public void setArrivalfromAbroad(Object ArrivalfromAbroad) {
                    this.ArrivalfromAbroad = ArrivalfromAbroad;
                }

                public Object getCustomsClearance() {
                    return CustomsClearance;
                }

                public void setCustomsClearance(Object CustomsClearance) {
                    this.CustomsClearance = CustomsClearance;
                }

                public Object getDestinationArrived() {
                    return DestinationArrived;
                }

                public void setDestinationArrived(Object DestinationArrived) {
                    this.DestinationArrived = DestinationArrived;
                }

                public String getWeblink() {
                    return weblink;
                }

                public void setWeblink(String weblink) {
                    this.weblink = weblink;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getCarrier_code() {
                    return carrier_code;
                }

                public void setCarrier_code(String carrier_code) {
                    this.carrier_code = carrier_code;
                }

                public List<TrackinfoBean> getTrackinfo() {
                    return trackinfo;
                }

                public void setTrackinfo(List<TrackinfoBean> trackinfo) {
                    this.trackinfo = trackinfo;
                }

                public static class TrackinfoBean {
                    /**
                     * Date : 2019-07-25 22:02:05
                     * StatusDescription : 吉林市【吉林昌邑区双吉镇】，【姜小晖/15688983811】正在派件
                     * Details : 吉林市
                     * checkpoint_status : pickup
                     * ItemNode : ItemReceived
                     */

                    private String Date;
                    private String StatusDescription;
                    private String Details;
                    private String checkpoint_status;
                    private String ItemNode;

                    public String getDate() {
                        return Date;
                    }

                    public void setDate(String Date) {
                        this.Date = Date;
                    }

                    public String getStatusDescription() {
                        return StatusDescription;
                    }

                    public void setStatusDescription(String StatusDescription) {
                        this.StatusDescription = StatusDescription;
                    }

                    public String getDetails() {
                        return Details;
                    }

                    public void setDetails(String Details) {
                        this.Details = Details;
                    }

                    public String getCheckpoint_status() {
                        return checkpoint_status;
                    }

                    public void setCheckpoint_status(String checkpoint_status) {
                        this.checkpoint_status = checkpoint_status;
                    }

                    public String getItemNode() {
                        return ItemNode;
                    }

                    public void setItemNode(String ItemNode) {
                        this.ItemNode = ItemNode;
                    }
                }
            }
        }
    }
}
