import { NavigationContainer } from '@react-navigation/native';
import type { NativeStackNavigationOptions } from '@react-navigation/native-stack';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import React, { useEffect } from 'react';
import { ToastProvider } from 'react-native-toast-notifications';

import { AddLottery } from './screens/AddLottery';
import { Home } from './screens/Home';
import { RegisterModal } from './screens/RegisterModal.tsx';
import type { RootStackParamList } from './types';
import { NativeModules } from 'react-native';

const { Notification } = NativeModules;

const Stack = createNativeStackNavigator<RootStackParamList>();

const options: NativeStackNavigationOptions = {
  title: '',
};

const queryClient = new QueryClient();

export const App = () => {
  useEffect(() => {
    console.log('NativeModules.Notification:', Notification);
    console.log('All NativeModules:', Object.keys(NativeModules));
    if (Notification) {
      Notification.requestPermissions();
    } else {
      console.error('Notification module is null/undefined');
    }
  }, []);

  return (
    <QueryClientProvider client={queryClient}>
      <ToastProvider>
        <NavigationContainer>
          <Stack.Navigator>
            <Stack.Group>
              <Stack.Screen name="Home" component={Home} options={options} />
              <Stack.Screen
                name="AddLottery"
                component={AddLottery}
                options={options}
              />
            </Stack.Group>
            <Stack.Group screenOptions={{ presentation: 'modal' }}>
              <Stack.Screen name="Register" component={RegisterModal} />
            </Stack.Group>
          </Stack.Navigator>
        </NavigationContainer>
      </ToastProvider>
    </QueryClientProvider>
  );
};
