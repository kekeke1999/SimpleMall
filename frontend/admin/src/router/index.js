import Vue from 'vue';
import Router from 'vue-router';
import login from '@/components/login';
import home from '@/components/home';
import categoryList from "@/components/categoryList";
import productList from "@/components/productList";
import offProducts from "@/components/offProducts";
import allProducts from "@/components/allProducts";
import unpayedOrder from "@/components/unpayedOrder";
import undeliveredOrder from "@/components/undeliveredOrder";
import finishedOrder from "@/components/finishedOrder";
import custom from "@/components/custom";
import info from "@/components/info";
import unconfirmedOrder from "@/components/unconfirmedOrder";

Vue.use(Router);

const router = new Router({
	routes: [
		{
			path: '/',
			redirect: '/home'
		},
		{
			path: '/login',
			name: 'login',
			component: login
		},
		{
			path: '/home',
			name: 'home',
			component: home
		},
		{
			path: '/category',
			name: 'category',
			component: categoryList
		},
		{
			path: '/product',
			name: 'product',
			component: productList
		},
		{
			path: '/offProducts',
			name: 'offProducts',
			component: offProducts
		},
		{
			path: '/allProducts',
			name: 'allProducts',
			component: allProducts
		},
		{
			path: '/unpayedOrder',
			name: 'unpayedOrder',
			component: unpayedOrder
		},
		{
			path: '/undeliveredOrder',
			name: 'undeliveredOrder',
			component: undeliveredOrder
		},
		{
			path: '/unconfirmedOrder',
			name: 'unconfirmedOrder',
			component: unconfirmedOrder
		},
		{
			path: '/finishedOrder',
			name: 'finishedOrder',
			component: finishedOrder
		},
		{
			path: '/custom',
			name: 'custom',
			component: custom
		},
		{
			path: '/info',
			name: 'info',
			component: info
		},
	]
});

// 导航守卫
// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
	if (to.path === '/login') {
		next();
	} else {
		let token = localStorage.getItem('Authorization');

		if (token === 'null' || token === '') {
			next('/login');
		} else {
			next();
		}
	}
});

export default router;
