const {Pool} = require('pg');

const pool = new Pool({
    user: 'postgres',
    host: 'localhost',
    database: 'or-labos',
    password: 'bazepodataka',
    port: 5432,
});

/*const pgPoolWrapper = {
  async connect() {
      for (let nRetry = 1; ; nRetry++) {
          try {
              const client = await pool.connect();
              if (nRetry > 1) {
                  console.log('Now successfully connected to Postgres');
              }
              return client;
          } catch (e) {
              if (e.toString().includes('ECONNREFUSED') && nRetry < 5) {
                  console.log('ECONNREFUSED connecting to Postgres, ' +
                      'maybe container is not ready yet, will retry ' + nRetry);
                  // Wait 1 second
                  await new Promise(resolve => setTimeout(resolve, 1000));
              } else {
                  throw e;
              }
          }
      }
  }
};
*/
module.exports = {
    query: (text, params) => {
        const start = Date.now();
        return pool.query(text, params)
            .then(res => {
                const duration = Date.now() - start;
                console.log('executed query', {text, params, duration, rows: res.rows});
                return res;
            });
    }
}
/*

const { Client } = require('pg')

const client = new Client({
  host: 'localhost',
  port: 5432,
  user: 'postgres',
  password: 'bazepodataka',
})

client
  .connect()
  .then(() => console.log('connected'))
  .catch(err => console.error('connection error', err.stack))

*/
/*
const updateUser = (request, response) => {
    const id = parseInt(request.params.id)
    const { name, email } = request.body
  
    pool.query(
      'UPDATE users SET name = $1, email = $2 WHERE id = $3',
      [name, email, id],
      (error, results) => {
        if (error) {
          throw error
        }
        response.status(200).send(`User modified with ID: ${id}`)
      }
    )
  }
  
  const deleteUser = (request, response) => {
    const id = parseInt(request.params.id)
  
    pool.query('DELETE FROM users WHERE id = $1', [id], (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).send(`User deleted with ID: ${id}`)
    })
  }
  
  module.exports = {
    updateUser,
    deleteUser
  }
*/
