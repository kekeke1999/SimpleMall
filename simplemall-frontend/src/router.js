import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

const routes = [
  {
    path: '*',
    redirect: '/login'
  },
  {
    name: 'user',
    component: () => import('./view/user'),
    meta: {
      title: '会员中心'
    }
  },
  {
    name: 'addressSave',
    component: () => import('./view/addressSave'),
    meta: {
      title: '新增地址'
    }
  },
  {
    name: 'undeliveredDetail',
    component: () => import('./view/undeliveredDetail'),
    meta: {
      title: '订单详情'
    }
  },
  {
    name: 'unpayedDetail',
    component: () => import('./view/unpayedDetail'),
    meta: {
      title: '订单详情'
    }
  },
  {
    name: 'unconfirmedDetail',
    component: () => import('./view/unconfirmedDetail'),
    meta: {
      title: '订单详情'
    }
  },
  {
    name: 'finishedDetail',
    component: () => import('./view/finishedDetail'),
    meta: {
      title: '订单详情'
    }
  },
  {
    name: 'addressEdit',
    component: () => import('./view/addressEdit'),
    meta: {
      title: '编辑地址'
    }
  },
  {
    name: 'cart',
    component: () => import('./view/cart'),
    meta: {
      title: '购物车'
    }
  },
  {
    name: 'order',
    component: () => import('./view/order'),
    meta: {
      title: '未支付订单'
    }
  },
  {
    name: 'goods',
    component: () => import('./view/goods'),
    meta: {
      title: '商品详情'
    }
  },
  {
    name: 'home',
    component: () => import('./view/home'),
    meta: {
      title: '主页'
    }
  },
  {
    name: 'category',
    component: () => import('./view/category'),
    meta: {
      title: '分类'
    }
  },
  {
    name: 'address',
    component: () => import('./view/address'),
    meta: {
      title: '地址列表'
    }
  },
  {
    name: 'login',
    component: () => import('./view/login'),
    meta: {
      title: '登录'
    }
  },
  {
    name: 'me',
    component: () => import('./view/me'),
    meta: {
      title: '个人信息'
    }
  },
];

// add route path
routes.forEach(route => {
  route.path = route.path || '/' + (route.name || '');
});

const router = new Router({ routes });


router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    next();
  } else {
    let token = localStorage.getItem('token');

    if (token === 'null' || token === '') {
      next('/login');
    } else {
      next();
    }
  }
});

export {
  router
};
