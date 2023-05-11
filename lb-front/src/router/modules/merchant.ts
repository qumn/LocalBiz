const merchant: AuthRoute.Route = {
  name: 'merchant',
  path: '/merchant',
  component: 'basic',
  meta: { title: '商家管理', icon: 'mdi:menu' },
  children: [
    {
      name: 'merchant_register',
      path: '/merchant/register',
      component: 'self',
      meta: { title: '注册', icon: 'mdi:menu' }
    },
    {
      name: 'merchant_management',
      path: '/merchant/management',
      component: 'self',
      meta: { title: '管理界面', icon: 'mdi:menu' }
    },
    {
      name: 'merchant_order',
      path: '/merchant/order',
      component: 'self',
      meta: { title: '订单管理', icon: 'mdi:menu' }
    }
  ]
};

export default merchant;
