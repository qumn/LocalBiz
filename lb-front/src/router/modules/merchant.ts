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
    }
  ]
};

export default merchant;


