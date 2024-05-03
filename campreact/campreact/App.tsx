/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, { useState } from 'react';
import { StyleSheet, Text, View, Image, TouchableOpacity, TextInput } from 'react-native';
import LogoIoasys from './src/assets/images/logo_ioasys.png'

const App = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  return (
  <View style={ styles.container }> 
    <Image source={LogoIoasys} style={ styles.logo } />
    <View style={ styles.textContainer }>
      <Text style={ styles.title }>Seja bem vindo!</Text>
      <Text style={ styles.subtitle }>Calculadora IMC</Text>
    </View>
    <TextInput style={ styles.input } placeholder='Usuário' value={username} onChangeText={setUsername} />
    <TextInput style={ styles.input } placeholder='Senha' value={password} onChangeText={setPassword} />
    <TouchableOpacity 
      style={ styles.button } 
      onPress={ () => console.log('Pressionado', username) }
      >
      <Text style={ styles.buttonText }>Entrar</Text>
    </TouchableOpacity>
  </View>
  )
}

const styles = StyleSheet.create( {
  container: {
    backgroundColor: '#C1007E',
    flex: 1,
    paddingLeft: 36,
    paddingRight: 39
  },

  logo: {
    height: 75,
    marginTop: 76,
    alignSelf: 'center'
  },

  button: {
    backgroundColor: '#020002',
    borderRadius: 100,
    paddingTop: 15,
    paddingBottom: 11
  },

  buttonText: {
    color: 'white',
    textAlign: 'center',
    fontSize: 16
  },

  input: {
    paddingTop: 14,
    paddingLeft: 24,
    paddingBottom: 12,
    backgroundColor: 'white',
    borderRadius: 100,
    marginBottom: 20
  },

  title: {
    fontSize: 31,
    color: 'white'
  },

  subtitle: {
    fontSize: 24,
    color: 'white'
  },

  textContainer: {
    marginTop: 129,
    marginBottom: 43
  }
});

export default App;
