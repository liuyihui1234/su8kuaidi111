package org.kuaidi.bean.trackingmore;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/8/21 11:58
 * @description rackingMore系统创建多个运单号，返回数据VO
 */
public class BatchVo {

    /**
     * meta : {"code":201,"type":"Created","message":"The request was successful and a resource was created."}
     * data : {"submitted":4,"added":3,"trackings":[{"id":"badc8f47c16c6b595ed8541d109dbbc2","tracking_number":"51837622007298","carrier_code":"bestex","order_create_time":0,"status":"pending","created_at":"2019-08-21T14:14:55+08:00","customer_email":null,"customer_name":null,"customer_phone":null,"order_id":null,"comment":null,"title":null,"logistics_channel":null,"destination":""},{"id":"9eee8f3e935e2b6c4767345e599bf673","tracking_number":"3502339401241","carrier_code":"bestex","order_create_time":0,"status":"pending","created_at":"2019-08-21T14:14:55+08:00","customer_email":null,"customer_name":null,"customer_phone":null,"order_id":null,"comment":null,"title":null,"logistics_channel":null,"destination":""},{"id":"df321e020066b0e4b5f5f2e0c094d7b7","tracking_number":"518376220072981","carrier_code":"bestex","order_create_time":0,"status":"pending","created_at":"2019-08-21T14:14:55+08:00","customer_email":null,"customer_name":null,"customer_phone":null,"order_id":null,"comment":null,"title":null,"logistics_channel":null,"destination":""}],"errors":[{"tracking_number":"350233940124","carrier_code":"bestex","code":4016,"message":"Tracking already exists."}]}
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
         * code : 201
         * type : Created
         * message : The request was successful and a resource was created.
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
         * submitted : 4
         * added : 3
         * trackings : [{"id":"badc8f47c16c6b595ed8541d109dbbc2","tracking_number":"51837622007298","carrier_code":"bestex","order_create_time":0,"status":"pending","created_at":"2019-08-21T14:14:55+08:00","customer_email":null,"customer_name":null,"customer_phone":null,"order_id":null,"comment":null,"title":null,"logistics_channel":null,"destination":""},{"id":"9eee8f3e935e2b6c4767345e599bf673","tracking_number":"3502339401241","carrier_code":"bestex","order_create_time":0,"status":"pending","created_at":"2019-08-21T14:14:55+08:00","customer_email":null,"customer_name":null,"customer_phone":null,"order_id":null,"comment":null,"title":null,"logistics_channel":null,"destination":""},{"id":"df321e020066b0e4b5f5f2e0c094d7b7","tracking_number":"518376220072981","carrier_code":"bestex","order_create_time":0,"status":"pending","created_at":"2019-08-21T14:14:55+08:00","customer_email":null,"customer_name":null,"customer_phone":null,"order_id":null,"comment":null,"title":null,"logistics_channel":null,"destination":""}]
         * errors : [{"tracking_number":"350233940124","carrier_code":"bestex","code":4016,"message":"Tracking already exists."}]
         */

        private int submitted;
        private int added;
        private List<TrackingsBean> trackings;
        private List<ErrorsBean> errors;

        public int getSubmitted() {
            return submitted;
        }

        public void setSubmitted(int submitted) {
            this.submitted = submitted;
        }

        public int getAdded() {
            return added;
        }

        public void setAdded(int added) {
            this.added = added;
        }

        public List<TrackingsBean> getTrackings() {
            return trackings;
        }

        public void setTrackings(List<TrackingsBean> trackings) {
            this.trackings = trackings;
        }

        public List<ErrorsBean> getErrors() {
            return errors;
        }

        public void setErrors(List<ErrorsBean> errors) {
            this.errors = errors;
        }

        public static class TrackingsBean {
            /**
             * id : badc8f47c16c6b595ed8541d109dbbc2
             * tracking_number : 51837622007298
             * carrier_code : bestex
             * order_create_time : 0
             * status : pending
             * created_at : 2019-08-21T14:14:55+08:00
             * customer_email : null
             * customer_name : null
             * customer_phone : null
             * order_id : null
             * comment : null
             * title : null
             * logistics_channel : null
             * destination :
             */

            private String id;
            private String tracking_number;
            private String carrier_code;
            private int order_create_time;
            private String status;
            private String created_at;
            private Object customer_email;
            private Object customer_name;
            private Object customer_phone;
            private Object order_id;
            private Object comment;
            private Object title;
            private Object logistics_channel;
            private String destination;

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

            public int getOrder_create_time() {
                return order_create_time;
            }

            public void setOrder_create_time(int order_create_time) {
                this.order_create_time = order_create_time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public Object getCustomer_email() {
                return customer_email;
            }

            public void setCustomer_email(Object customer_email) {
                this.customer_email = customer_email;
            }

            public Object getCustomer_name() {
                return customer_name;
            }

            public void setCustomer_name(Object customer_name) {
                this.customer_name = customer_name;
            }

            public Object getCustomer_phone() {
                return customer_phone;
            }

            public void setCustomer_phone(Object customer_phone) {
                this.customer_phone = customer_phone;
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

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public Object getLogistics_channel() {
                return logistics_channel;
            }

            public void setLogistics_channel(Object logistics_channel) {
                this.logistics_channel = logistics_channel;
            }

            public String getDestination() {
                return destination;
            }

            public void setDestination(String destination) {
                this.destination = destination;
            }
        }

        public static class ErrorsBean {
            /**
             * tracking_number : 350233940124
             * carrier_code : bestex
             * code : 4016
             * message : Tracking already exists.
             */

            private String tracking_number;
            private String carrier_code;
            private int code;
            private String message;

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

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }
    }
}
