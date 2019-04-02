var app = new Vue({
   el: '#app',
   data: {
       list: [
           {
               id: 1,
               name: 'data1',
               price: 120,
               count: 1
           },
           {
               id: 2,
               name: 'data2',
               price: 1400,
               count: 1
           },
           {
               id: 3,
               name: 'data3',
               price: 1356,
               count: 1
           }
       ]
   },
   computed: {
       totalPrice: function () {
           var total = 0;
           for (var i = 0; i < this.list.length; i++) {
               var item = this.list[i];
               total += item.price * item.count;
           }

           return total.toString()
       }

   },
   methods: {
       btnClickReduce: function (index) {
           if (this.list[index].count === 1) {
               return;
           }
           this.list[index].count--;
       },
       btnClickAdd: function (index) {
           this.list[index].count++;
       },
       btnClickRemove: function (index) {
           this.list.splice(index, 1);
       }
   }
});